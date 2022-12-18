package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "medical category request")
public class MedicalCategoryRequest {

    @NotBlank
    @Schema(description = "medical category name")
    @JsonProperty("name")
    private String name;
}
