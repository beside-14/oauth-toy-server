package com.bside.v8.global.response

class ApiResponseDto<T> private constructor(
        private var data: T,
        private var code: ApiResponseCode
) {
    companion object Factory {
        fun <T> OK(data: T): ApiResponseDto<T> {
            return ApiResponseDto(data, ApiResponseCode.OK)
        }
    }
    private var message = code.message
}