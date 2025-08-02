package bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GroupToPackCreateDto {
    @NotNull
    private String name;
}
