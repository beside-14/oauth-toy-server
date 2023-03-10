package com.bside.v8.domain.member.api

import com.bside.v8.domain.member.application.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping
    fun find() {
    }

    @PatchMapping
    fun update() {
    }
}
