package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.AuthenticationService
import com.bside.v8.domain.member.dto.request.AuthenticationRequest
import com.bside.v8.domain.member.dto.response.AuthenticationResponse
import com.bside.v8.global.response.ApiResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/api/v1/auth/authenticate")
    fun authenticate(
        @RequestBody authenticationRequest: AuthenticationRequest
    ): ApiResponseDto<AuthenticationResponse> {
        val token = authenticationService.authentication(authenticationRequest.command())
        val response = AuthenticationResponse(token)
        return ApiResponseDto.OK(response)
    }
}
