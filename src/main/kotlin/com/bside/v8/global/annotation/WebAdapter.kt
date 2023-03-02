package com.bside.v8.global.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RestController

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@RestController
annotation class WebAdapter(
    @get:AliasFor(annotation = RestController::class)
    val value: String = ""
)
