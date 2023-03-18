package com.bside.v8.domain.member.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByEmail(email: String): Member?

    fun existsByEmail(email: String): Boolean
}