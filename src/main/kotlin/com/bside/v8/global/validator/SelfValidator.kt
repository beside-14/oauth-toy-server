package com.bside.v8.global.validator

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation

abstract class SelfValidator {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    open fun validation() {
        val violation = this.validator.validate(this)
        if (violation.isNotEmpty()) throw ConstraintViolationException(violation)
    }
}
