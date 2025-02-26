package org.example.sbertest.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutUserResponse {
    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    private String email;
    @Schema(description = "id пользователя", example = "cf617b5b-52c1-476f-96b0-67330690eb52")
    private UUID id;
    @Schema(description = "Имя пользователя", example = "Johnny ")
    private String name;
}
