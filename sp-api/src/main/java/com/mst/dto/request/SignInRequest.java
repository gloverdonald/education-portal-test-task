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
@Schema(description = "User Sign In Request")
public class SignInRequest {

    @Schema(description = "User email", example = "montelacey@gmail.com")
    @JsonProperty("email")
    private String email;

    @Schema(description = "User password", example = "Password123_")
    @JsonProperty("password")
    private String password;
}