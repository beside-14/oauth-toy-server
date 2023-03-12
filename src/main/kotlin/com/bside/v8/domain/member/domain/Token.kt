package com.bside.v8.domain.member.domain

import com.bside.v8.global.domain.jpa.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType

@Entity
@Table(name = "token")
class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "token", unique = true)
    val token: String,

    @Column(name = "token_type")
    val tokenType: String = TokenType.BEARER.value,

    @Column(name = "revoked")
    var revoked: Boolean,

    @Column(name = "expired")
    var expired: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false)
    val member: Member
) : BaseEntity() {

    fun renew() {
        revoked = true
        expired = true
    }
}
