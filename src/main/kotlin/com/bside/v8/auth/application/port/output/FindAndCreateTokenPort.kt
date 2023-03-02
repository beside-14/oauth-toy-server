package com.bside.v8.auth.application.port.output

interface FindAndCreateTokenPort {

    fun findAndCreate(email: String): String
}
