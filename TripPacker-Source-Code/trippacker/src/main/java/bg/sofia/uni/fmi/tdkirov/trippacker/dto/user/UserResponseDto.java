package bg.sofia.uni.fmi.tdkirov.trippacker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;

    private String createdAt;
}
