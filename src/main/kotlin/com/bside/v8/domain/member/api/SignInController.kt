package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.SignInService
import com.bside.v8.domain.member.dto.request.SignInRequest
import com.bside.v8.domain.member.dto.response.TokenResponse
import com.bside.v8.global.response.ApiResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController(
    private val signInService: SignInService
) {

    @PostMapping("/member/sign-in")
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ApiResponseDto<TokenResponse> {
        val token = signInService.signIn(signInRequest.command())
        val response = TokenResponse(token)
        return ApiResponseDto.OK(response)
    }
}
