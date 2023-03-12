package com.bside.v8.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder.getContext
import java.util.Optional

@Configuration
@EnableJpaAuditing
class AuditingConfig {

    @Bean
    fun auditorProvider(): AuditorAware<String> =
        AuditorAware {
            Optional.of(
                getContext().authentication.principal.toString()
            )
        }
}
