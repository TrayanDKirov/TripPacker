package bg.sofia.uni.fmi.tdkirov.trippacker.mapper;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class TripLuggageMapper {
    private PackingItemMapper packingItemMapper;
    private PackingGroupMapper packingGroupMapper;

    public TripLuggage toEntity(TripLuggageCreateDto tripLuggageDto, User user) {
        return new TripLuggage(tripLuggageDto.getName(), user);
    }

    public TripLuggageResponseDto toResponseDto(TripLuggage tripLuggage) {
        Set<PackingGroupResponseDto> packingGroups = new LinkedHashSet<>();
        for (PackingGroup curr : tripLuggage.getPackingGroups()) {
            packingGroups.add(packingGroupMapper.toResponseDto(curr));
        }

        Set<PackingItemResponseDto> packingItems = new LinkedHashSet<>();
        for (PackingItem curr : tripLuggage.getPackingItems()) {
            packingItems.add(packingItemMapper.toResponseDto(curr));
        }

        return new TripLuggageResponseDto(tripLuggage.getName(), packingGroups,
            packingItems, tripLuggage.getCreatedAt());
    }
}
