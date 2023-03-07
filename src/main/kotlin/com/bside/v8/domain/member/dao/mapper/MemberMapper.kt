package com.bside.v8.domain.member.dao.mapper

import com.bside.v8.domain.member.domain.Member
import com.bside.v8.domain.member.dto.domain.MemberDto
import com.bside.v8.global.annotation.Mapper

@Mapper
class MemberMapper {

    fun mapToEntity(memberDto: MemberDto): Member = Member(
        nickname = memberDto.nickName,
        email = memberDto.email,
        pw = memberDto.password,
        platform = memberDto.platform
    )
}
