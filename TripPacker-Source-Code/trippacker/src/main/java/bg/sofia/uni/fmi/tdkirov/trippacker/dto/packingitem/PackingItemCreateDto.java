package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PackingItemCreateDto {
    @NotNull
    private Long itemId;

    private Long tripId;
    private Long groupId;
}
