package com.bside.v8.global.domain.security

import com.bside.v8.domain.member.domain.MemberRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Component
class JwtProvider {
    companion object {
        private const val SECRET = "77848c3af62f4699e4d1ede93555864c2306bb49f0554e2fa9aaad9225d582ca4d17bca04df0459b4f170c462b92d48708a1e73090823bf7275e9580d52e6347"
        private val KEY: Key? = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET))

        fun createToken(email: String, role: MemberRole): String {
            val issuedAt = LocalDateTime.now()
            val issuedDate = Date.from(issuedAt.atZone(ZoneId.systemDefault()).toInstant())

            val expiration = LocalDateTime.now().plusHours(6)
            val expirationDate = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant())

            val claims = mapOf<String, Any>("email" to email, "role" to role.name)

            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(KEY, SignatureAlgorithm.HS512)
                    .setIssuedAt(issuedDate)
                    .setExpiration(expirationDate)
                    .compact()
        }
        fun getAuthentication(token: String?): Authentication {
            val claims = getAllClaims(token)
            val email = claims["email"]
            val authorities = listOf(claims["role"])
                    .map { role -> SimpleGrantedAuthority(role as String?) }
            return UsernamePasswordAuthenticationToken(email, token, authorities)
        }

        fun isValidToken(token: String?): Boolean {
            return getAllClaims(token).expiration
                    .after(Date())
        }

        private fun getAllClaims(token: String?): Claims {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .body
        }
    }
}