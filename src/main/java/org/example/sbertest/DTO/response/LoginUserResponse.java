package org.example.sbertest.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sbertest.modules.RoleEnum;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResponse {
    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    private String email;
    @Schema(description = "id пользователя", example = "cf617b5b-52c1-476f-96b0-67330690eb52")
    private UUID id;
    @Schema(description = "Имя пользователя", example = "Johnny ")
    private String name;
    @Schema(description = "Роль пользователя", example = "[\"USER\"]")
    private Set<RoleEnum> roles;
    @Schema(description = "Токен пользователя", example = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlkIjoiMjA1OTllOGEtZDhlNC00MjZmLWE0ZjQtNTJiNzQ0ZDA2Mzc0Iiwic3ViIjoiMjA1OTllOGEtZDhlNC00MjZmLWE0ZjQtNTJiNzQ0ZDA2Mzc0IiwiaWF0IjoxNzQwNTYwMTY3LCJleHAiOjE3NDA1OTYxNjd9.TQtIN8MFqxS6RQJEyASzOq6h_bckJJuIiZLN-VtE5NE")
    private String token;
}
