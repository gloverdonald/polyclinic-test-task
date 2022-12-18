package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "file response")
public class FileResponse {

    @Schema(description = "file name", example = "image.png")
    @JsonProperty("name")
    private String name;

    @Schema(description = "type name", example = "image/jpeg")
    @JsonProperty("type")
    private String type;

    @Schema(description = "type size", example = "4102501")
    @JsonProperty("size")
    private String size;

    @Schema(description = "file bytes")
    @JsonProperty("bytes")
    private byte[] bytes;
}
