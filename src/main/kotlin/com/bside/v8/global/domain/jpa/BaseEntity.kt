package com.bside.v8.global.domain.jpa

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity : BaseCreatedTimeEntity() {

    @LastModifiedDate
    private var lastModifiedDate = LocalDateTime.MIN
        private set

    @LastModifiedBy
    private var lastModifiedBy = ""
        private set
}