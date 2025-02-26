package org.example.sbertest.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.sbertest.constants.Constants;
import org.example.sbertest.constants.ValidationConstants;

@Data
public class AuthUserRequest {

    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Pattern(regexp = Constants.EMAIL_VALIDATION, message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;

    @Schema(description = "Пароль пользователя", example = "P@ssw0rd")
    @NotBlank(message = ValidationConstants.PASSWORD_NOT_VALID)
    private String password;
}
