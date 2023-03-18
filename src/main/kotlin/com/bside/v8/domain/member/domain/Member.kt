package com.bside.v8.domain.member.domain

import com.bside.v8.global.domain.jpa.BaseEntity
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "members")
class Member(
        @Id
        @Column(name = "member_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        val email: String,

        private val password: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: MemberRole = MemberRole.MEMBER

) : BaseEntity() {
    fun matches(password: String, passwordEncoder: BCryptPasswordEncoder) {
            passwordEncoder.matches(password, this.password)
    }
}