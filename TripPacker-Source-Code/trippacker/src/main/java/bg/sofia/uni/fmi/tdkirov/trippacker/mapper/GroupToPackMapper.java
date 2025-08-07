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

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class GroupToPackMapper {
    private ItemToPackMapper itemToPackMapper;

    public GroupToPack toEntity(GroupToPackCreateDto packingGroupDto, User user) {
        GroupToPack result = new GroupToPack(packingGroupDto.getName(), null);
        result.setCreatedBy(user);

        return result;
    }

    public GroupToPackResponseDto toResponseDto(GroupToPack groupToPack) {
        Set<ItemToPackResponseDto> items = new LinkedHashSet<>();
        for (ItemToPack curr : groupToPack.getItems()) {
            items.add(itemToPackMapper.toResponseDto(curr));
        }

        return new GroupToPackResponseDto(groupToPack.getId(), groupToPack.getName(),
            items, groupToPack.getCreatedAt());
    }


    public GroupToPackPreviewDto toPreviewDto(GroupToPack groupToPack) {
        return new GroupToPackPreviewDto(groupToPack.getId(), groupToPack.getName(), groupToPack.getCreatedAt());
    }
}
