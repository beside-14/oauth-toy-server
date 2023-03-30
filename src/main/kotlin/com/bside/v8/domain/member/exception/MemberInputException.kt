package com.bside.v8.domain.member.exception

class MemberInputException(
        override val message: String
) : RuntimeException("[MemberInputException] $message")