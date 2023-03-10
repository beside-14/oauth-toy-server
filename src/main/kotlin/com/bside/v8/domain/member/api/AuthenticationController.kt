package com.bside.v8.domain.member.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class AuthenticationController {

    @PostMapping
    fun signIn() {
    }

    @PostMapping("/sign-up")
    fun signUp() {
    }
}
