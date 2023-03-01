package com.bside.v8.user.adapter.output.repository

import com.bside.v8.user.adapter.output.entity.EUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<EUser, Long>, QuerydslPredicateExecutor<EUser> {

    fun findByEmail(email: String): Optional<EUser>
}
