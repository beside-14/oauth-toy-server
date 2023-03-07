package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.SignUpService
import com.bside.v8.domain.member.dto.request.SignUpRequest
import com.bside.v8.domain.member.dto.response.AuthenticationResponse
import com.bside.v8.global.response.ApiResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(
    private val signUpService: SignUpService
) {

    @PostMapping("/api/v1/auth/register")
    fun register(
        @RequestBody signUpRequest: SignUpRequest
    ): ApiResponseDto<AuthenticationResponse> {
        val token = signUpService.register(signUpRequest.command())
        val response = AuthenticationResponse(token)
        return ApiResponseDto.OK(response)
    }
}
