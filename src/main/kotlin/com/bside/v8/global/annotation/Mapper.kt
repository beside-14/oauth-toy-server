package com.bside.v8.global.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class Mapper(
    @get:AliasFor(annotation = Component::class)
    val value: String = ""
)
