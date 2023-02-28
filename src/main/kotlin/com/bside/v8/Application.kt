package com.bside.v8

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthToyServerApplication

fun main(args: Array<String>) {
    runApplication<OauthToyServerApplication>(*args)
}
