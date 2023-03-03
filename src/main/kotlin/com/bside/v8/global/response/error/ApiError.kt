package com.bside.v8.global.response.error

import com.bside.v8.global.response.ApiResponseCode
import java.time.LocalDateTime

class ApiError(
        private val code: ApiResponseCode,
        e: Throwable
) {
    private val message = code.message
    private val timestamp = LocalDateTime.now()
    private var debugMessage = e.toString()
}