package com.bside.v8.domain.member.dao.repository

import com.bside.v8.domain.member.domain.Member
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface MemberPredicateExecutor : QuerydslPredicateExecutor<Member>
