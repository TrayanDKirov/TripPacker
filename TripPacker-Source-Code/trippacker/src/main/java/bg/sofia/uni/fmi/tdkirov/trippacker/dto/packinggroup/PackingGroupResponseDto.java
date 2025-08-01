package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Data
public class PackingGroupResponseDto {
    private Long id;
    private String name;
    private Set<ItemToPackResponseDto> items;
    private LocalDateTime createdAt;
}
