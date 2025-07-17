package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PackingGroupCreateDto {
    @NotNull
    private String name;
}
