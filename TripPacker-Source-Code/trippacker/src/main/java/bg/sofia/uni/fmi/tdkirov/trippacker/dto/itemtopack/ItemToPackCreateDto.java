package bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemToPackCreateDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    private int quantityToPack;

    private Long tripId;
    private Long packingGroupId;
}
