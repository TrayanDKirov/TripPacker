package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class PackingItemResponseDto {
    private Boolean isPacked;

    private String name;

    private Integer quantityToPack;
    private Integer packedQuantity;

    private LocalDateTime createdAt;
}
