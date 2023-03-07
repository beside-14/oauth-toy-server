package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.SignUpService
import com.bside.v8.domain.member.dto.request.SignUpRequest
import com.bside.v8.domain.member.dto.response.TokenResponse
import com.bside.v8.global.response.ApiResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(
    private val signUpService: SignUpService
) {

    @PostMapping("/member/sign-up")
    fun signUp(
        @RequestBody signUpRequest: SignUpRequest
    ): ApiResponseDto<TokenResponse> {
        val token = signUpService.signUp(signUpRequest.command())
        val response = TokenResponse(token)
        return ApiResponseDto.OK(response)
    }
}
