package com.hikmatullo.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data @AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "message in the response"
    )
    private String message;

    @Schema(
            description = "Status message in the response"
    )
    private HttpStatus status;

}
