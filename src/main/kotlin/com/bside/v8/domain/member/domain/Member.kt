package com.bside.v8.domain.member.domain

import com.bside.v8.domain.model.Platform
import com.bside.v8.global.domain.jpa.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "member")
class Member(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val pw: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    val platform: Platform = Platform.EMAIL
) : UserDetails, BaseEntity() {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(this.platform.name))

    override fun getPassword(): String = this.pw

    override fun getUsername(): String = this.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
