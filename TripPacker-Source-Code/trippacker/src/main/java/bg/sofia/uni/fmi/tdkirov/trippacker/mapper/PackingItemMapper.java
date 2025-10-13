package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemCreationException;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import org.springframework.stereotype.Component;

@Component
public class PackingItemMapper {
    private void assertValidCreateDto(PackingItemCreateDto packingItemDto) {
        if ((packingItemDto.getGroupId() == null  && packingItemDto.getTripId() == null) ||
            (packingItemDto.getGroupId() != null  && packingItemDto.getTripId() != null)) {
            throw new PackingItemCreationException();
        }
    }

    public PackingItem toEntity(PackingItemCreateDto packingItemDto, User user) {
        assertValidCreateDto(packingItemDto);
        PackingItem result;
        ItemToPack itemToPack = new ItemToPack(packingItemDto.getItemId());

        if (packingItemDto.getTripId() != null) {
            TripLuggage trip = new TripLuggage(packingItemDto.getTripId());

            result = new PackingItem(itemToPack, trip, null, user);
        }
        else {
            PackingGroup packingGroup = new PackingGroup(packingItemDto.getGroupId());

            result = new PackingItem(itemToPack, null, packingGroup, user);
        }

        return result;
    }

    public PackingItemResponseDto toResponseDto(PackingItem packingItem) {
        ItemToPack itemToPack = packingItem.getItem();

        return new PackingItemResponseDto(packingItem.getId(), packingItem.getIsPacked(), itemToPack.getName(),
            itemToPack.getQuantityToPack(), packingItem.getPackedQuantity());
    }
}
