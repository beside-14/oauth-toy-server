package com.bside.v8.global.manager

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date

@Service
class JwtManager {

    /**
     * 암호화 키
     */
    private final val KEY =
        "432646294A404E635266556A586E3272357538782F413F442A472D4B61506453"

    /**
     * JWT 내부에서 유저 이메일 찾기
     */
    fun extractUserName(jwt: String): String = this.extractClaim(jwt) { it.subject }

    /**
     * JWT 내부에서 Claim 객체 찾기
     */
    fun <T> extractClaim(
        token: String,
        claimsResolver: (Claims) -> T
    ): T {
        val claims = this.extractAllClaims(token)
        return claimsResolver.invoke(claims)
    }

    /**
     * JWT 토큰 정의
     */
    fun generateToken(userDetails: UserDetails): String =
        this.generateToken(emptyMap(), userDetails)

    /**
     * JWT 토큰 정의
     */
    fun generateToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails
    ): String = Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.username)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact()

    /**
     * JWT 토큰 유효성 검사
     */
    fun isTokenValid(
        token: String,
        userDetails: UserDetails // Token 정보가 UserDetails 에 속하는지 확인하기 위해서 필요하다.
    ): Boolean {
        val userName = extractUserName(token)
        return userName == userDetails.username && !isTokenExpired(token)
    }

    /**
     * JWT 토큰 유효기간 검사
     */
    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    /**
     * JWT 내부에서 유효기간 찾기
     */
    private fun extractExpiration(token: String): Date = extractClaim(token) { it.expiration }

    /**
     * JWT 내부에서 모든 Claim 객체 찾기
     */
    private fun extractAllClaims(token: String): Claims =
        Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey()) // JWT 의 서명 부분을 생성하는 Key > 메세지가 중간에 변경되지 않았는지 확인한다.
            .build()
            .parseClaimsJws(token)
            .body

    /**
     * BASE64 기반 암호화 인증키 생성
     */
    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
