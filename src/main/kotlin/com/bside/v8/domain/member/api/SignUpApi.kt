package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.SignUpService
import com.bside.v8.domain.member.payload.request.SignUpRequest
import com.bside.v8.domain.member.payload.response.SignUpResponse
import com.bside.v8.global.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class SignUpApi(
        private val signUpService: SignUpService
) {
    @PostMapping
    fun signUp(@RequestBody request: SignUpRequest
    ): ApiResponse<SignUpResponse> {
        val signUpResponse = signUpService.signUp(request.command())
        return ApiResponse.OK(signUpResponse)
    }
}