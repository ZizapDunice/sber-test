package org.example.sbertest.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.sbertest.constants.ValidationConstants;

@Data
public class UpdateStatusUserRequest {

    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = 3, max = 100, message = ValidationConstants.EMAIL_SIZE_NOT_VALID)
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;

    @Schema(description = "Статус пользователя", example = "true")
    @NotBlank(message = ValidationConstants.USER_STATUS_NOT_NULL)
    boolean active;

}
