package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class PackingGroupMapper { // TODO check response dto
    private ItemToPackMapper itemToPackMapper;

    public PackingGroup toEntity(PackingGroupCreateDto packingGroupDto, User user) {
        PackingGroup result = new PackingGroup(packingGroupDto.getName(), null);
        result.setCreatedBy(user);

        return result;
    }

    public PackingGroupResponseDto toResponseDto(PackingGroup packingGroup) {
        Set<ItemToPackResponseDto> items = new LinkedHashSet<>();
        for (ItemToPack curr : packingGroup.getItems()) {
            items.add(itemToPackMapper.toResponseDto(curr));
        }

        return new PackingGroupResponseDto(packingGroup.getId(), packingGroup.getName(),
            items, packingGroup.getCreatedAt());
    }
}
