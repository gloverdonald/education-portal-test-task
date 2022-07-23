package com.mst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mst.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Response")
public class UserResponse {

    @Schema(description = "User id", example = "1")
    @JsonProperty("id")
    private Long id;

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

    @Schema(description = "User role", example = "USER")
    @JsonProperty("role")
    private Role role;

    @Schema(description = "User CV")
    @JsonProperty("cv")
    private CVResponse cv;

    @Schema(description = "User academy group")
    @JsonProperty("academy_group")
    private AcademyGroupResponse academyGroup;

    @Schema(description = "User avatar id")
    @JsonProperty("avatar_id")
    private Long avatarId;
}