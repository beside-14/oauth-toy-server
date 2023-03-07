package com.bside.v8.user.domain.entity

import com.bside.v8.user.domain.enumerate.Platform
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member(
    @Id
    @Column(name = "id", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "nickname", columnDefinition = "varchar", length = 20)
    val nickname: String,

    @Column(name = "email", columnDefinition = "varchar", length = 255)
    val email: String,

    @Column(name = "password", columnDefinition = "mediumtext")
    val pw: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", columnDefinition = "varchar", length = 15)
    val platform: Platform = Platform.EMAIL
) : UserDetails {

    @CreatedDate
    @Column(
        name = "created_at",
        columnDefinition = "timestamp",
        updatable = false,
        insertable = false
    )
    val createdAt: LocalDateTime = LocalDateTime.now()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(this.platform.name))

    override fun getPassword(): String = this.pw

    override fun getUsername(): String = this.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
