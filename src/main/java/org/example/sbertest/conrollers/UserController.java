package org.example.sbertest.conrollers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.example.sbertest.DTO.request.PutUserRequest;
import org.example.sbertest.DTO.response.PublicUserResponse;
import org.example.sbertest.DTO.response.PutUserResponse;
import org.example.sbertest.DTO.response.commonResponse.BaseSuccessResponse;
import org.example.sbertest.DTO.response.commonResponse.CustomErrorResponse;
import org.example.sbertest.DTO.response.commonResponse.CustomSuccessResponse;
import org.example.sbertest.constants.Constants;
import org.example.sbertest.constants.ValidationConstants;
import org.example.sbertest.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/getAllUsers")
    @Operation(summary = "Получить всех пользователей")
    @ApiResponse(responseCode = "200", description = "Список пользователей получен")
    public ResponseEntity<CustomSuccessResponse<List<PublicUserResponse>>> getAllUsers() {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.getAllUsers()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Информация о пользователе получена")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Пользователя не существует",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    public ResponseEntity<CustomSuccessResponse<PublicUserResponse>> getUserInfoById(@PathVariable
                                                                                     @Pattern(regexp = Constants.UUID_VALIDATION, message = ValidationConstants.INVALID_UUID_FORMAT)
                                                                                     String id) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.getUserInfoById(UUID.fromString(id))));
    }

    @GetMapping("/info")
    @Operation(summary = "Получить информацию о своем аккаунте")
    @ApiResponse(responseCode = "200", description = "Информация о пользователе получена")
    public ResponseEntity<CustomSuccessResponse<PublicUserResponse>> getUserInfo() {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.getUserInfo()));
    }

    @PutMapping
    @Operation(summary = "Обновить данные пользователя")
    @ApiResponse(responseCode = "200", description = "Информация о пользователе получена")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    public ResponseEntity<CustomSuccessResponse<PutUserResponse>> replaceUser(@Valid
                                                                              @RequestBody
                                                                              PutUserRequest userRequest) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(service.replaceUser(userRequest)));
    }

    @PostMapping("/{id}/block")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Заблокировать пользователя(Только для ADMIN)")
    @ApiResponse(responseCode = "200", description = "Пользователь заблокирован")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    public ResponseEntity<BaseSuccessResponse> blockUser(@PathVariable
                                                         @Pattern(regexp = Constants.UUID_VALIDATION, message = ValidationConstants.INVALID_UUID_FORMAT)
                                                         String id) {
        service.blockUser(UUID.fromString(id));
        return ResponseEntity.ok(new BaseSuccessResponse());
    }

    @PostMapping("/{id}/unblock")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Разблокировать пользователя(Только для ADMIN)")
    @ApiResponse(responseCode = "200", description = "Пользователь разблокирован")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))
    )
    public ResponseEntity<BaseSuccessResponse> unblockUser(@PathVariable
                                                           @Pattern(regexp = Constants.UUID_VALIDATION, message = ValidationConstants.INVALID_UUID_FORMAT)
                                                           String id) {
        service.unblockUser(UUID.fromString(id));
        return ResponseEntity.ok(new BaseSuccessResponse());
    }

    @DeleteMapping
    @Operation(summary = "Удалить свой аккаунт пользователя")
    @ApiResponse(responseCode = "200", description = "Аккаунт удален")
    public ResponseEntity<BaseSuccessResponse> deleteUser() {
        service.deleteUser();
        return ResponseEntity.ok(new BaseSuccessResponse());
    }
}
