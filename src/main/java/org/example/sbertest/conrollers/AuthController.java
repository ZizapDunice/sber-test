package org.example.sbertest.conrollers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sbertest.DTO.request.AuthUserRequest;
import org.example.sbertest.DTO.request.RegisterUserRequest;
import org.example.sbertest.DTO.response.LoginUserResponse;
import org.example.sbertest.DTO.response.commonResponse.CustomErrorResponse;
import org.example.sbertest.DTO.response.commonResponse.CustomSuccessResponse;
import org.example.sbertest.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints для регистрации и аутентификации")
public class AuthController {

    private final AuthService service;

    @Operation(summary = "Регистрация пользователя", description = "Создание нового пользователя")
    @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован")
    @PostMapping("/register")
    public ResponseEntity<CustomSuccessResponse<LoginUserResponse>> signUp(@Valid @RequestBody RegisterUserRequest userDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.signUp(userDto)));
    }

    @Operation(summary = "Аутентификация пользователя", description = "Вход пользователя в систему")
    @ApiResponse(responseCode = "200", description = "Успешный вход")
    @ApiResponse(responseCode = "401", description = "Ошибка аутентификации",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Пользователя не существует",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    @PostMapping("/login")
    public ResponseEntity<CustomSuccessResponse<LoginUserResponse>> signIn(@Valid @RequestBody AuthUserRequest userDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.signIn(userDto)));
    }
}
