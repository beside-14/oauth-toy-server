package com.bside.v8.global.filter

import com.bside.v8.global.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        when (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
            true -> {
                filterChain.doFilter(request, response)
                return
            }

            else -> {
                val jwt = authHeader.substring(7)
                val userEmail = jwtService.extractUserName(jwt)
            }
        }
    }
}
