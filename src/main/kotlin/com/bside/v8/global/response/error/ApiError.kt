package com.bside.v8.global.response.error

import com.bside.v8.global.response.ApiResponseCode
import java.time.LocalDateTime

data class ApiError(
    val code: ApiResponseCode,
    private val e: Throwable
) {
    val message = code.message
    val timestamp = LocalDateTime.now()
    val debugMessage = e.toString()
}
