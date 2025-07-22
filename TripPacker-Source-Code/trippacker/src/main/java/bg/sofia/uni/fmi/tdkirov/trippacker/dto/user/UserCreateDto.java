package bg.sofia.uni.fmi.tdkirov.trippacker.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
