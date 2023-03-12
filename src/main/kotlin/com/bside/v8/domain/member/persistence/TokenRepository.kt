package com.bside.v8.domain.member.persistence

import com.bside.v8.domain.member.domain.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TokenRepository : JpaRepository<Token, Long> {

    fun findByToken(token: String): Optional<Token>

    @Query(
        """
        select t from Token t inner join Member m
        on t.member.id = m.id
        where m.id = :memberId and (t.expired = false or t.revoked = false)
    """
    )
    fun findAllValidTokenByMember(memberId: Long): List<Token>
}
