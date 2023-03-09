package com.bside.v8.domain.member.dto.command

import com.bside.v8.global.manager.ValidationManager
import jakarta.validation.constraints.NotBlank

data class SignInCommand(
    @NotBlank
    val email: String,
    @NotBlank
    val password: String
) : ValidationManager() {
    init {
        validation()
    }
}
