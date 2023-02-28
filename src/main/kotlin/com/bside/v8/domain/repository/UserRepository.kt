package com.bside.v8.domain.repository

import com.bside.v8.domain.entity.EUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<EUser, Long>, QuerydslPredicateExecutor<EUser>
