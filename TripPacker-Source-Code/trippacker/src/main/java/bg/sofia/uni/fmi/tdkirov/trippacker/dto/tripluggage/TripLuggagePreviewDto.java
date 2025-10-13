package bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripLuggagePreviewDto {
    private Long id;
    private String name;
    private String createdAt;
}
