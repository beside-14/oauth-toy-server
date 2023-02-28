package com.bside.v8.domain.entity

import com.bside.v8.domain.enumerate.Platform
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class EUser(
    @Id
    @Column(name = "id", columnDefinition = "int")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nickname", columnDefinition = "varchar", length = 20)
    val nickname: String,

    @Column(name = "email", columnDefinition = "varchar", length = 255)
    val email: String,

    @Column(name = "password", columnDefinition = "mediumtext")
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", columnDefinition = "varchar", length = 15)
    val platform: Platform = Platform.EMAIL
) {
    @CreatedDate
    @Column(
        name = "created_at",
        columnDefinition = "timestamp",
        updatable = false,
        insertable = false
    )
    val createdAt: LocalDateTime = LocalDateTime.now()
}
