package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "medical category response")
public class MedicalCategoryResponse {
    @Schema(description = "medical category id")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "medical category name")
    @JsonProperty("name")
    private String name;
}
