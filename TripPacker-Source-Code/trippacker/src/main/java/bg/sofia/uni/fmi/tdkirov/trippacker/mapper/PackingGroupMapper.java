package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class PackingGroupMapper {
    private PackingItemMapper packingItemMapper;

    public PackingGroup toEntity(PackingGroupCreateDto packingGroupDto, TripLuggage trip, User user) {
        GroupToPack groupToPack = new GroupToPack(packingGroupDto.getGroupToPackId());

        return new PackingGroup(new LinkedHashSet<>(), groupToPack, trip, user);
    }

    public PackingGroupResponseDto toResponseDto(PackingGroup packingGroup) {
        Set<PackingItemResponseDto> itemsToSend = new LinkedHashSet<>();
        for (PackingItem currItem : packingGroup.getItems()) {
            itemsToSend.add(packingItemMapper.toResponseDto(currItem));
        }

        return new PackingGroupResponseDto(packingGroup.getId(), packingGroup.getGroup().getName(), itemsToSend);
    }
}
