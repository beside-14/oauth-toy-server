package com.bside.v8.domain.member.persistence

import com.bside.v8.domain.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository : JpaRepository<Member, Long> {

    fun findByEmail(email: String): Optional<Member>
}
