package com.bside.v8.auth.application.port.output

import com.bside.v8.auth.domain.User

interface RegisterUserAndCreateTokenPort {

    fun registerAndCreate(user: User): String
}
