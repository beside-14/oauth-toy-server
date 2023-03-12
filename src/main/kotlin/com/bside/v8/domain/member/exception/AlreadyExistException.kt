package com.bside.v8.domain.member.exception

import org.springframework.security.core.AuthenticationException

class AlreadyExistException : AuthenticationException("이미 존재하는 회원 입니다.") {
}
