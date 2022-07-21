package com.mst.api;

import com.mst.dto.request.*;
import com.mst.dto.response.SuccessResponse;
import com.mst.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/users")
public interface UserApi<PRINCIPAL extends UserDetails> {

    @Operation(summary = "Getting basic information about the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),})
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable("id") Long userId);

    @Operation(summary = "Getting a user avatar")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "File bytes")})
    @GetMapping("/{id}/photo")
    ResponseEntity<byte[]> getUserAvatar(@PathVariable("id") Long userId);

    @Operation(summary = "Updating user information")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})})
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserRequest userRequest);

    @Operation(summary = "Updating user group")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})})
    @PutMapping("/academy-group/{group_id}")
    ResponseEntity<UserResponse> updateUserGroup(@PathVariable("group_id") Long groupId, @AuthenticationPrincipal PRINCIPAL userPrincipal);

    @Operation(summary = "Updating user cv")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})})
    @PutMapping("/cv")
    ResponseEntity<UserResponse> updateUserCV(@RequestBody CVRequest cvRequest, @AuthenticationPrincipal PRINCIPAL userPrincipal);

    @Operation(summary = "Updating user avatar")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})})
    @PutMapping("/photo")
    ResponseEntity<UserResponse> updateUserAvatar(@RequestBody AvatarUpdateRequest avatarUpdateRequest, @AuthenticationPrincipal PRINCIPAL userPrincipal);

    @Operation(summary = "Updating user password")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @PutMapping("/password")
    ResponseEntity<SuccessResponse> updateUserPassword(@RequestBody @Valid PasswordUpdateRequest passwordUpdateRequest, @AuthenticationPrincipal PRINCIPAL userPrincipal);
}