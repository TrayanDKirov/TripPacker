package bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PackingItemUpdateDto {
    private Boolean isPacked;

    private Integer packedQuantity;
}
