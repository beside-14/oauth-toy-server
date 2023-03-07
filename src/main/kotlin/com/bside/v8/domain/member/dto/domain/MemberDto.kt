package com.bside.v8.domain.member.dto.domain

import com.bside.v8.domain.model.Platform

data class MemberDto(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
)
