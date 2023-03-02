package com.bside.v8.auth.adapter.input.controller

import com.bside.v8.auth.adapter.input.request.RegisterRequest
import com.bside.v8.auth.adapter.input.response.AuthenticationResponse
import com.bside.v8.auth.application.port.input.RegisterUseCase
import com.bside.v8.global.annotation.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter
class RegisterController(
    private val registerUseCase: RegisterUseCase
) {

    @PostMapping("/api/v1/auth/register")
    fun register(
        @RequestBody registerRequest: RegisterRequest
    ): ResponseEntity<AuthenticationResponse> {
        val token = registerUseCase.register(registerRequest.command())
        val response = AuthenticationResponse(token)

        return ResponseEntity.ok(response)
    }
}
