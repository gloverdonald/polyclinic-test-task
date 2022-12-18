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
@Schema(description = "medical service response")
public class MedicalCareResponse {
    @Schema(description = "medical service id")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "medical service name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "medical service price")
    @JsonProperty("price")
    private Integer price;

    @Schema(description = "medical service description")
    @JsonProperty("description")
    private String description;

    @Schema(description = "medical category id")
    @JsonProperty("category_id")
    private UUID categoryId;
}
