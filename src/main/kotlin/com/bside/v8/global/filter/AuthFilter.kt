package com.bside.v8.global.filter

import com.bside.v8.domain.member.application.JwtService
import com.bside.v8.domain.member.persistence.TokenRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,
    private val tokenRepository: TokenRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        // HTTP Header 에서 특정 값을 추출
        val authHeader = request.getHeader("Authorization")

        // HTTP Header 에 JWT 토큰을 담은 Key 가 존재하지 않는다면
        if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        // JWT 추출
        val jwt = authHeader.substring(7)

        // 유저 Email
        val userEmail = jwtService.extractUserName(jwt)

        // 과거에 인증 이력이 존재하는 유저인 경우
        if (userEmail.isNotBlank() && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(userEmail)
            val isTokenValid = tokenRepository.findByToken(jwt)
                .map { !it.expired && !it.revoked }
                .orElse(false)

            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                val authToken =
                    UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }
}
