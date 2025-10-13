package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class PackingGroupResponseDto {
    private Long id;
    private String name;

    private Set<PackingItemResponseDto> items;
}
