package com.bside.v8.domain.member.dto.command

import com.bside.v8.domain.model.Platform
import com.bside.v8.global.manager.ValidationManager
import jakarta.validation.constraints.NotBlank

data class SignUpCommand(
    @NotBlank
    val nickName: String,
    @NotBlank
    val email: String,
    @NotBlank
    val password: String,
    @NotBlank
    val platform: Platform
) : ValidationManager() {
    init {
        validation()
    }
}
