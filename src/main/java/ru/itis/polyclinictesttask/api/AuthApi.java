package ru.itis.polyclinictesttask.api;

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
import ru.itis.polyclinictesttask.dto.request.NewPasswordRequest;
import ru.itis.polyclinictesttask.dto.request.SignInRequest;
import ru.itis.polyclinictesttask.dto.request.SignUpRequest;
import ru.itis.polyclinictesttask.dto.request.TokenRefreshRequest;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.dto.response.TokensResponse;

import javax.validation.Valid;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @Operation(summary = "Sign Up")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "New User",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponse.class))}),})
    @PostMapping(value = "/sign-up")
    ResponseEntity<AccountResponse> createUser(@RequestBody SignUpRequest signUpRequest);

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
