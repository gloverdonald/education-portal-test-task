package com.mst.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mst.validation.annotation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Request")
public class UserRequest {

    @NotBlank
    @Schema(description = "User name", example = "Monte")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Schema(description = "User name", example = "Lacey")
    @JsonProperty("last_name")
    private String lastName;

    @PhoneNumber
    @NotBlank
    @Schema(description = "User phone")
    @JsonProperty("phone")
    private String phone;

    @NotNull
    @Schema(description = "User birthdate")
    @JsonProperty("birthdate")
    private Date birthdate;

    @NotBlank
    @Schema(description = "User address")
    @JsonProperty("address")
    private String address;

    @NotBlank
    @Schema(description = "User telegram")
    @JsonProperty("telegram")
    private String telegram;
}