package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.MemberService
import com.bside.v8.domain.member.payload.response.MemberResponse
import com.bside.v8.global.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/{memberId}")
    fun find(
        @PathVariable memberId: Long
    ): ApiResponse<MemberResponse> {
        val member = memberService.find(memberId)
        return ApiResponse.OK(MemberResponse(member))
    }
}
