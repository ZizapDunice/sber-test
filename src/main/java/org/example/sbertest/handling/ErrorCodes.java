package org.example.sbertest.handling;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.sbertest.constants.ValidationConstants;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@Getter
@Schema(description = "Перечисление возможных ошибок API")
public enum ErrorCodes {
    UNKNOWN(0, "unknown", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_SIZE_NOT_VALID(1, ValidationConstants.USERNAME_SIZE_NOT_VALID, HttpStatus.BAD_REQUEST),
    ROLE_SIZE_NOT_VALID(2, "role size not valid", HttpStatus.BAD_REQUEST),
    EMAIL_SIZE_NOT_VALID(3, "email size not valid", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4, "Could not find user", HttpStatus.NOT_FOUND),
    UNAUTHORISED(5, "unauthorised", HttpStatus.UNAUTHORIZED),
    USER_EMAIL_NOT_NULL(6, "user email mustn't be null", HttpStatus.BAD_REQUEST),
    USER_ROLE_NOT_NULL(7, "user role mustn't be null", HttpStatus.BAD_REQUEST),
    USER_EMAIL_NOT_VALID(8, "user email must be a well-formed email address", HttpStatus.BAD_REQUEST),
    MAX_UPLOAD_SIZE_EXCEEDED(9, "Maximum upload size exceeded", HttpStatus.PAYLOAD_TOO_LARGE),
    PASSWORD_NOT_VALID(10, "password not valid", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS(11, "User already exists", HttpStatus.CONFLICT),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(12, ValidationConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION, HttpStatus.BAD_REQUEST),
    INVALID_UUID_FORMAT(13, ValidationConstants.INVALID_UUID_FORMAT, HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCodes(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static final Map<String, ErrorCodes> ERROR_MAP = new HashMap<>();

    static {
        for (ErrorCodes errorCode : ErrorCodes.values()) {
            ERROR_MAP.put(errorCode.message, errorCode);
        }
    }

    public static ErrorCodes fromMessage(String message) {
        return ERROR_MAP.getOrDefault(message, UNKNOWN);
    }
}

