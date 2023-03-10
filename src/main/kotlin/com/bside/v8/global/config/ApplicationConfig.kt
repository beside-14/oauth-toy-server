package com.bside.v8.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig(
    private val customUserDetailService: UserDetailsService
) {

    /**
     * AuthenticationProvider?
     * - UserDetails 를 가져오고, 비밀번호를 인코딩하는 데이터 엑세스 개체이다.
     */
    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(customUserDetailService)
        provider.setPasswordEncoder(passwordEncoder())

        return provider
    }

    /**
     * AuthenticationManager?
     * - 사용자 이름 혹은 이름과 비밀번호를 기반으로 인증하는 방식이나 이를 위한 인증 저장소를 만드는 방식을 제공한다.
     *
     */
    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    /**
     * 비밀번호 인코더
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
