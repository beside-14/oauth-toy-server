package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.AuthService
import com.bside.v8.domain.member.payload.request.AuthRequest
import com.bside.v8.domain.member.payload.request.SignUpRequest
import com.bside.v8.domain.member.payload.response.TokenResponse
import com.bside.v8.global.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    fun authentication(
        @RequestBody request: AuthRequest
    ): ApiResponse<TokenResponse> {
        val token = authService.authenticate(request.command())
        return ApiResponse.OK(TokenResponse(token))
    }

    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody request: SignUpRequest
    ): ApiResponse<TokenResponse> {
        val token = authService.signUp(request.command())
        return ApiResponse.OK(TokenResponse(token))
    }
}
