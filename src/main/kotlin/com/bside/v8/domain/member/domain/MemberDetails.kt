package com.bside.v8.domain.member.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class MemberDetails(
    private val member: Member
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(
        SimpleGrantedAuthority(this.member.role.name)
    )

    override fun getPassword(): String = member.password

    override fun getUsername(): String = member.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    fun newToken(jwt: String): Token = Token(
        token = jwt,
        expired = false,
        revoked = false,
        member = member
    )
}
