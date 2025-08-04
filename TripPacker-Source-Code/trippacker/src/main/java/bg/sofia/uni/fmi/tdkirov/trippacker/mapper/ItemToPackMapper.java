package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackCreationException;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import org.springframework.stereotype.Component;

@Component
public class ItemToPackMapper {
    private void assertItemToPackCreateDtoIsValid(ItemToPackCreateDto itemToPackDto) {
        if ((itemToPackDto.getPackingGroupId() == null && itemToPackDto.getTripId() == null) ||
        itemToPackDto.getPackingGroupId() != null && itemToPackDto.getTripId() != null) {
            throw new ItemToPackCreationException();
        }
    }

    public ItemToPack toEntity(ItemToPackCreateDto itemToPackDto, User user) {
        assertItemToPackCreateDtoIsValid(itemToPackDto);
        ItemToPack result;

        if (itemToPackDto.getPackingGroupId() != null) {
            GroupToPack groupToPack = new GroupToPack(itemToPackDto.getPackingGroupId());

            result = new ItemToPack(itemToPackDto.getName(), itemToPackDto.getQuantityToPack(), user, groupToPack, null);
        }
        else {
            TripLuggage tripLuggage = new TripLuggage(itemToPackDto.getTripId());

            result = new ItemToPack(itemToPackDto.getName(), itemToPackDto.getQuantityToPack(), user, null, tripLuggage);
        }

        return result;
    }

    public ItemToPackResponseDto toResponseDto(ItemToPack itemToPack) {
        return new ItemToPackResponseDto(itemToPack.getId(), itemToPack.getName(),
            itemToPack.getQuantityToPack(), itemToPack.getCreatedAt());
    }
}
