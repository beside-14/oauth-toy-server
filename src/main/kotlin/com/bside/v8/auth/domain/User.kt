package com.bside.v8.auth.domain

import com.bside.v8.user.domain.enumerate.Platform

data class User(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
)
