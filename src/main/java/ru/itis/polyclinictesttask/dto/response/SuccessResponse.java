package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "success Response")
public class SuccessResponse {

    @Schema(description = "message")
    @JsonProperty("message")
    private String message;

    @Schema(description = "data")
    @JsonProperty("data")
    private Instant data;
}