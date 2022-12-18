package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "page response")
public class PageResponse<T> {

    @Schema(description = "list of responses")
    @JsonProperty("responses")
    private List<T> responses;

    @Schema(description = "current page")
    @JsonProperty("current_page")
    private Integer currentPage;

    @Schema(description = "total pages")
    @JsonProperty("total_pages")
    private Integer totalPages;

    @Schema(description = "total Elements")
    @JsonProperty("total_elements")
    private Long totalElements;

}
