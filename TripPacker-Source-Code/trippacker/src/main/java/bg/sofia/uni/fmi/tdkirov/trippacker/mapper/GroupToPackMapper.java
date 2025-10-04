package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackPreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class GroupToPackMapper {
    private ItemToPackMapper itemToPackMapper;
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private final static String DATE_ONLY_PATTERN = "dd MMM yy";
    private final static DateTimeFormatter DATE_ONLY_FORMATTER =
        DateTimeFormatter.ofPattern(DATE_ONLY_PATTERN);

    public GroupToPack toEntity(GroupToPackCreateDto groupToPackDto, User user) {
        GroupToPack result = new GroupToPack(groupToPackDto.getName(), null);
        result.setCreatedBy(user);

        return result;
    }

    public GroupToPackPreviewDto toPreviewDto(GroupToPack groupToPack) {
        return new GroupToPackPreviewDto(groupToPack.getId(), groupToPack.getName(),
            DATE_ONLY_FORMATTER.format(groupToPack.getCreatedAt()));
    }

    public GroupToPackResponseDto toResponseDto(GroupToPack groupToPack) {
        Set<ItemToPackResponseDto> items = new LinkedHashSet<>();
        for (ItemToPack curr : groupToPack.getItems()) {
            items.add(itemToPackMapper.toResponseDto(curr));
        }

        return new GroupToPackResponseDto(groupToPack.getId(), groupToPack.getName(),
            items, FORMATTER.format(groupToPack.getCreatedAt()));
    }
}
