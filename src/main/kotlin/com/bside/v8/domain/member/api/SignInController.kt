package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.MemberService
import com.bside.v8.domain.member.dto.request.SignInRequest
import com.bside.v8.domain.member.dto.response.TokenResponse
import com.bside.v8.global.response.ApiResponseDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController(
    private val memberService: MemberService
) {

    @PostMapping("/member")
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ): ApiResponseDto<TokenResponse> {
        val token = memberService.signIn(signInRequest.command())
        val response = TokenResponse(token)
        return ApiResponseDto.OK(response)
    }
}
