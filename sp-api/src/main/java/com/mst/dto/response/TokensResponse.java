package com.mst.dto.response;

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
@Schema(description = "Tokens Response")
public class TokensResponse {

    @Schema(description = "Access Token", example = "BEARER eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtc3RvbHBv" +
            "dnNreW1AZ21haWwuY29tIiwicm9sZXMiOlsiVVNFUiJdLCJpYXQiOjE2NTc1NTEzMjksImV4cCI6MTY1NzU1MTgyOX0." +
            "RlVM9-s1cxmfBX0sMWQ5196Iz7XpW4lDPnR-LeLm-wszZIwUpLu_QbSaLOZsQmgbDG_LWvef_q2_FQtQTxo-wg")
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(description = "Refresh Token", example = "f1d0a99c-8744-4548-9115-c9248367631f")
    @JsonProperty("refresh_token")
    private String refreshToken;
}