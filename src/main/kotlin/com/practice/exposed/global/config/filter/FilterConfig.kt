package com.practice.exposed.global.config.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.exposed.global.config.jwt.TokenProvider
import com.practice.exposed.global.filter.ExceptionFilter
import com.practice.exposed.global.filter.JwtTokenFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val tokenProvider: TokenProvider,
    private val objectMapper: ObjectMapper
): SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val jwtFilter = JwtTokenFilter(tokenProvider)
        val exceptionFilter = ExceptionFilter(objectMapper)
        builder.addFilterBefore(exceptionFilter, UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}