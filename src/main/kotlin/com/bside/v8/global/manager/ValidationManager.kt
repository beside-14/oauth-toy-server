package com.bside.v8.global.manager

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation

abstract class ValidationManager {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    open fun validation() {
        val violation = this.validator.validate(this)
        if (violation.isNotEmpty()) throw ConstraintViolationException(violation)
    }
}
