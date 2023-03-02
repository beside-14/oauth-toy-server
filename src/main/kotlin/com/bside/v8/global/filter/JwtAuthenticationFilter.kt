package com.bside.v8.global.filter

import com.bside.v8.global.manager.JwtManager
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
class JwtAuthenticationFilter(
    private val jwtManager: JwtManager,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        // HTTP Header 에서 특정 값을 추출
        val authHeader = request.getHeader("Authorization")

        // HTTP Header 에 JWT 토큰을 담은 Key 가 존재하지 않는 다면
        if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        // JWT 추출
        val jwt = authHeader.substring(7)

        // 유저 Email
        val userEmail = jwtManager.extractUserName(jwt)

        // 인증받지 않은 유저인 경우
        if (userEmail.isNotBlank() && SecurityContextHolder.getContext().authentication == null) {
            // DB 에서 유저 Email 을 기준으로 유저 데이터 조회 > 존재하지 않으면 예외 발생
            val userDetails = this.userDetailsService.loadUserByUsername(userEmail)

            // 유저 데이터의 인증 유효성 체크
            if (jwtManager.isTokenValid(jwt, userDetails)) {
                // 인증 토큰 생성
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, // 아직 인가 받지 않은 유저기 때문에 null 로 설정한다.
                    userDetails.authorities
                )
                // 인증 토큰 디테일 설정 > HTTP Request
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                // ContextHolder > 인증 정보 세팅
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }
}
