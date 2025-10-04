package bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class GroupToPackPreviewDto {
    private Long id;
    private String name;
    private String createdAt;
}
