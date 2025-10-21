package bg.sofia.uni.fmi.tdkirov.trippacker.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
