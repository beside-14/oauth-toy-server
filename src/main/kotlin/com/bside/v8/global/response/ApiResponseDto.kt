package com.bside.v8.global.response

class ApiResponseDto<T> private constructor(
        val data: T,
        val code: ApiResponseCode
) {
    companion object Factory {
        fun <T> OK(data: T): ApiResponseDto<T> {
            return ApiResponseDto(data, ApiResponseCode.OK)
        }
    }
    val message = code.message
}