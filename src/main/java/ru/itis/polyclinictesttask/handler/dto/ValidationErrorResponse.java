package ru.itis.polyclinictesttask.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {

    @JsonProperty("field")
    private String field;

    @JsonProperty("exception")
    private String exception;

    @JsonProperty("message")
    private String message;
}