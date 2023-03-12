package com.bside.v8.domain.member.domain

import com.bside.v8.global.domain.jpa.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    val platform: Platform = Platform.EMAIL,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: Role = Role.USER
) : BaseEntity()
