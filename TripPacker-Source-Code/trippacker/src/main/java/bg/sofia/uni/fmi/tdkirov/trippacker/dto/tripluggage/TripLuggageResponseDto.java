package bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Data
public class TripLuggageResponseDto {
    private String name;

    private Set<PackingGroupResponseDto> packingGroups;
    private Set<PackingItemResponseDto> packingItems;

    private LocalDateTime createdAt;
}
