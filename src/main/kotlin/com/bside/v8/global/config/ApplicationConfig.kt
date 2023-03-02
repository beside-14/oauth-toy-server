package com.bside.v8.global.config

import com.bside.v8.user.adapter.output.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig(
    private val userRepository: UserRepository
) {

    @Bean
    fun userDetailsService(): UserDetailsService = UserDetailsService {
        userRepository.findByEmail(it)
            .orElseThrow { UsernameNotFoundException("찾을 수 없는 유저입니다.") }
    }

    /**
     * AuthenticationProvider 란?
     * - UserDetails 를 가져오고, 비밀번호를 인코딩하는 데이터 엑세스 개체이다.
     */
    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService())
        provider.setPasswordEncoder(passwordEncoder())

        return provider
    }

    /**
     * 비밀번호 인코더
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
