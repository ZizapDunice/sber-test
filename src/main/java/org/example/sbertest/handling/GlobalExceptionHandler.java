package org.example.sbertest.handling;

import jakarta.validation.ConstraintViolationException;
import org.example.sbertest.DTO.response.commonResponse.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        List<Integer> errorCodes = ex.getBindingResult().getAllErrors().stream()
                .map(error -> getErrorCodeByMessage(error.getDefaultMessage()))
                .toList();

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorCodes, "Validation failed");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        List<Integer> errorCodes = ex.getConstraintViolations().stream()
                .map(error -> getErrorCodeByMessage(error.getMessage()))
                .toList();

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorCodes, "Constraint violation");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException ex) {
        return buildErrorResponse(ex.getHttpStatus(), List.of(ex.getErrorCode()), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException() {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                List.of(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getCode()),
                ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getMessage()
        );
    }

    private ResponseEntity<CustomErrorResponse> buildErrorResponse(HttpStatus status, List<Integer> errorCodes, String message) {
        return ResponseEntity
                .status(status)
                .body(new CustomErrorResponse(status.value(), errorCodes, message));
    }

    private int getErrorCodeByMessage(String message) {
        return ErrorCodes.ERROR_MAP.getOrDefault(message, ErrorCodes.UNKNOWN).getCode();
    }
}

