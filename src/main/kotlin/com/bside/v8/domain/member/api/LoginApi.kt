package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.LoginService
import com.bside.v8.domain.member.payload.request.LoginRequest
import com.bside.v8.domain.member.payload.response.LoginResponse
import com.bside.v8.global.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class LoginApi(
        private val loginService: LoginService
) {
    @PostMapping("/login")
    fun signUp(@RequestBody request: LoginRequest
    ): ApiResponse<LoginResponse> {
        val loginResponse = loginService.login(request.command())
        return ApiResponse.OK(loginResponse)
    }
}