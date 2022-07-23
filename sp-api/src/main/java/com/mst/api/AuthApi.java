package com.mst.api;

import com.mst.dto.request.NewPasswordRequest;
import com.mst.dto.request.SignInRequest;
import com.mst.dto.request.SignUpRequest;
import com.mst.dto.request.TokenRefreshRequest;
import com.mst.dto.response.TokensResponse;
import com.mst.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @Operation(summary = "Sign Up")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "New User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),})
    @PostMapping(value = "/sign-up")
    ResponseEntity<UserResponse> createUser(@RequestBody SignUpRequest signUpRequest);

    @Operation(summary = "Sign In")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Tokens",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TokensResponse.class))}),})
    @PostMapping(value = "/sign-in")
    ResponseEntity<TokensResponse> login(@RequestBody SignInRequest signInRequest);

    @Operation(summary = "Confirmation")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Tokens",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TokensResponse.class))}),})
    @PostMapping("/confirm/{confirm_code}")
    ResponseEntity<TokensResponse> confirm(@PathVariable("confirm_code") String parameter, @RequestBody @Valid NewPasswordRequest newPasswordRequest);

    @Operation(summary = "Tokens refresh")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Tokens",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TokensResponse.class))}),})
    @PostMapping("/refresh")
    ResponseEntity<TokensResponse> refresh(@RequestBody TokenRefreshRequest request);
}
