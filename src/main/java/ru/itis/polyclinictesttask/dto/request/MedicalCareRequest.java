package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "medical care request")
public class MedicalCareRequest {
    @Schema(description = "medical care name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "medical care price")
    @JsonProperty("price")
    private Integer price;

    @Schema(description = "medical care description")
    @JsonProperty("description")
    private String description;

    @Schema(description = "medical category id")
    @JsonProperty("category_id")
    private UUID categoryId;
}
