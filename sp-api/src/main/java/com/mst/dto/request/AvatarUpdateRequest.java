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
@Schema(description = "User Update Avatar Request")
public class AvatarUpdateRequest {
    @Schema(description = "New user avatar id")
    @JsonProperty("user_photo_id")
    private Long userPhotoId;
}
