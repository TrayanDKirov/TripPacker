package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class PackingGroupMapper { // TODO check response dto
    private ItemToPackMapper itemToPackMapper;

    public GroupToPack toEntity(PackingGroupCreateDto packingGroupDto, User user) {
        GroupToPack result = new GroupToPack(packingGroupDto.getName(), null);
        result.setCreatedBy(user);

        return result;
    }

    public PackingGroupResponseDto toResponseDto(GroupToPack groupToPack) {
        Set<ItemToPackResponseDto> items = new LinkedHashSet<>();
        for (ItemToPack curr : groupToPack.getItems()) {
            items.add(itemToPackMapper.toResponseDto(curr));
        }

        return new PackingGroupResponseDto(groupToPack.getId(), groupToPack.getName(),
            items, groupToPack.getCreatedAt());
    }
}
