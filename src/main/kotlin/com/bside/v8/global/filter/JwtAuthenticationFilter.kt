package com.bside.v8.global.filter

import com.bside.v8.global.domain.security.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
            return filterChain.doFilter(request, response)
        }
        val jwt = authHeader.substring("Bearer ".length)
        if (JwtProvider.isValidToken(jwt)) {
            SecurityContextHolder.getContext()
                    .authentication = JwtProvider.getAuthentication(jwt)
        }
        filterChain.doFilter(request, response)
    }
}