package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PackingGroupResponseDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;
}
