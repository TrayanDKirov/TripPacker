package bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TripLuggageUpdateDto {
    private String newName;

    private Set<PackingGroupCreateDto> newGroups;
    private PackingGroupCreateDto newGroup;
}
