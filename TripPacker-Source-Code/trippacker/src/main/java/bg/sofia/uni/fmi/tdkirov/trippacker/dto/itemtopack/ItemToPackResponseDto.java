package bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ItemToPackResponseDto {
    private Long id;
    private String name;
    private Integer quantityToPack;
    private LocalDateTime createdAt;
}
