package com.mst.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Sign Up Request")
public class SignUpRequest {

    @Schema(description = "User email", example = "montelacey@gmail.com")
    @JsonProperty("email")
    private String email;

    @Schema(description = "User name", example = "Monte")
    @JsonProperty("first_name")
    private String firstName;

    @Schema(description = "User name", example = "Lacey")
    @JsonProperty("last_name")
    private String lastName;
}