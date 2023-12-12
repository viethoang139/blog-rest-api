package com.leviethoang.controller;

import com.leviethoang.dto.JwtResponseDto;
import com.leviethoang.dto.LoginDto;
import com.leviethoang.dto.RegisterDto;
import com.leviethoang.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Tag(
        name = "REST APIs for Login and Register"
)
public class AuthController {
    private AuthService authService;
    @Operation(
            summary = "Login Rest Api",
            description = "Login Rest Api is used to login to application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid LoginDto loginDto){
        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setToken(authService.login(loginDto));
        return ResponseEntity.ok(jwtResponseDto);
    }

    @Operation(
            summary = "Register Rest Api",
            description = "Register Rest Api is used to register new account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }

}
