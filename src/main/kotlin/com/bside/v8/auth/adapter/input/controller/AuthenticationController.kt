package com.bside.v8.auth.adapter.input.controller

import com.bside.v8.auth.adapter.input.request.AuthenticationRequest
import com.bside.v8.auth.adapter.input.response.AuthenticationResponse
import com.bside.v8.auth.application.port.input.AuthenticationUseCase
import com.bside.v8.global.annotation.WebAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter
class AuthenticationController(
    private val authenticationUseCase: AuthenticationUseCase
) {

    @PostMapping("/api/v1/auth/authenticate")
    fun authenticate(
        @RequestBody authenticationRequest: AuthenticationRequest
    ): ResponseEntity<AuthenticationResponse> {
        val token = authenticationUseCase.authentication(authenticationRequest.command())
        val response = AuthenticationResponse(token)
        return ResponseEntity.ok(response)
    }
}
