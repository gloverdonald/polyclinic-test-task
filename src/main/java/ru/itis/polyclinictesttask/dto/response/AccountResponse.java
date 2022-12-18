package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Response")
public class AccountResponse {

    @Schema(description = "User id", example = "1")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "type", example = "DOCTOR")
    @JsonProperty("account_type")
    private String type;

    @Schema(description = "User first name", example = "Monte")
    @JsonProperty("first_name")
    private String firstName;

    @Schema(description = "User last name", example = "Lacey")
    @JsonProperty("last_name")
    private String lastName;

    @Schema(description = "User phone", example = "89314381273")
    @JsonProperty("phone")
    private String phone;

    @Schema(description = "User email", example = "montelacey@gmail.com")
    @JsonProperty("email")
    private String email;

    @Schema(description = "User birth date", example = "02-1-2018 06:07:59")
    @JsonProperty("birth_date")
    private Date birthDate;

    @Schema(description = "User telegram", example = "qweasdszxc")
    @JsonProperty("telegram")
    private String telegram;

    @Schema(description = "User address", example = "Address")
    @JsonProperty("address")
    private String address;

    @Schema(description = "User experience description", example = "some experience")
    @JsonProperty("experience_desc")
    private String experienceDesc;

    @Schema(description = "User portfolio links", example = "https://github.com/link123dsa")
    @JsonProperty("portfolio_links")
    private String portfolioLinks;


    @Schema(description = "User avatar id")
    @JsonProperty("avatar_id")
    private UUID avatarId;
}