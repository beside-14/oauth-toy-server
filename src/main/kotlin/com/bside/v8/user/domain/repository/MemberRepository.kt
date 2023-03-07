package com.bside.v8.user.domain.repository

import com.bside.v8.user.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository : JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    fun findByEmail(email: String): Optional<Member>
}
