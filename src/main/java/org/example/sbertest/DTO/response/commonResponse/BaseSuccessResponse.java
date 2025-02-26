package org.example.sbertest.DTO.response.commonResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseSuccessResponse {
    @Schema(description = "Статус код", example = "30")
    private Integer statusCode;

    private Boolean success = true;

    private List<Integer> codes;

    public BaseSuccessResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public BaseSuccessResponse(int statusCode, List<Integer> codes) {
        this.statusCode = statusCode;
        this.codes = codes;
    }
}
