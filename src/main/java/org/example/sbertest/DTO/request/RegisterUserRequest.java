package org.example.sbertest.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.sbertest.constants.ValidationConstants;
import org.example.sbertest.modules.RoleEnum;
import java.util.Set;

@Data
public class RegisterUserRequest {

    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;

    @Schema(description = "Имя пользователя", example = "Johnny ")
    @Size(min = 3, max = 25, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT)
    private String name;

    @Schema(description = "Пароль пользователя", example = "P@ssw0rd")
    @NotBlank(message = ValidationConstants.USER_PASSWORD_NULL)
    private String password;

    @Schema(description = "Роль пользователя", example = "[\"USER\"]")
    @Size(min = 1, message = ValidationConstants.ROLE_SIZE_NOT_VALID)
    @NotEmpty(message = ValidationConstants.ROLE_SIZE_NOT_VALID)
    private Set<RoleEnum> roles;

}
