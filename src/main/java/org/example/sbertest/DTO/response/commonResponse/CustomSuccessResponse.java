package org.example.sbertest.DTO.response.commonResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomSuccessResponse<T> extends BaseSuccessResponse {
    private T data;

    public CustomSuccessResponse(T data) {
        this.data = data;
    }

    public CustomSuccessResponse(int statusCode, T data) {
        super(statusCode);
        this.data = data;
    }
}
