package com.leviethoang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Error Details",
        description = "Schema to hold error details information"
)
public class ErrorDetails {
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDate timestamp;
    @Schema(
            description = "Error message representing the error happened"
    )
    private String message;
    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus status;
    @Schema(
            description = "API path invoked by client"
    )
    private String path;


}
