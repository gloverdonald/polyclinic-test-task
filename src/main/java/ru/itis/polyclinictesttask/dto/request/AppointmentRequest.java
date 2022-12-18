package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "appointment request")
public class AppointmentRequest {
    @Schema(description = "status")
    @JsonProperty("status_id")
    private String status;

    @Schema(description = "doctor")
    @JsonProperty("doctor_id")
    private UUID doctorId;

    @Schema(description = "medical care")
    @JsonProperty("medical_care_id")
    private UUID medicalCareId;

    @Schema(description = "patient")
    @JsonProperty("patient_id")
    private UUID patientId;

    @Schema(description = "commentary")
    @JsonProperty("commentary")
    private String commentary;

    @Schema(description = "anamnesis")
    @JsonProperty("anamnesis")
    private String anamnesis;

    @Schema(description = "startTime")
    @JsonProperty("start_time")
    private OffsetDateTime startTime;

    @Schema(description = "end_time")
    @JsonProperty("end_time")
    private OffsetDateTime endTime;

    @Schema(description = "links")
    @JsonProperty("links")
    private Set<String> links;
}
