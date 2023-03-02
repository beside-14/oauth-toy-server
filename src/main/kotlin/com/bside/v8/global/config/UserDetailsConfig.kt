package com.bside.v8.global.config

import com.bside.v8.user.adapter.output.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
class UserDetailsConfig(
    private val userRepository: UserRepository
) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService {
            userRepository.findByEmail(it)
                .orElseThrow { UsernameNotFoundException("찾을 수 없는 유저입니다.") }

        }
    }
}
