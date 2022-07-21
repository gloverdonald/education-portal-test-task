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
@Schema(description = "Refresh Token Request")
public class TokenRefreshRequest {

    @Schema(description = "Refresh Token", example = "f1d0a99c-8744-4548-9115-c9248367631f", type = "UUID")
    @JsonProperty("refresh_token")
    private String refreshToken;
}