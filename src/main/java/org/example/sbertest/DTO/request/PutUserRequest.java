package org.example.sbertest.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.sbertest.constants.Constants;
import org.example.sbertest.constants.ValidationConstants;

@Data
public class PutUserRequest {

    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @Pattern(regexp = Constants.EMAIL_VALIDATION, message = ValidationConstants.USER_EMAIL_NOT_VALID)@NotBlank(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    private String email;

    @Schema(description = "Имя пользователя", example = "Johnny ")
    @Size(min = 3, max = 25, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT)
    private String name;

}
