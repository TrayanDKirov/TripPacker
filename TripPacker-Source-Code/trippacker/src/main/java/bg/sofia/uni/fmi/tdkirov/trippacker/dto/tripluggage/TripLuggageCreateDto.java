package bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TripLuggageCreateDto {
    @NotNull
    private String name;
}
