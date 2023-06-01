package com.practice.exposed.global.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.exposed.global.config.filter.FilterConfig
import com.practice.exposed.global.security.jwt.TokenProvider
import com.practice.exposed.global.security.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            .requestMatchers(HttpMethod.POST, "/auth").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/auth").authenticated()

            .requestMatchers(HttpMethod.POST, "/postings").authenticated()
            .requestMatchers(HttpMethod.POST, "/postings/one").authenticated()
            .requestMatchers(HttpMethod.DELETE, "/postings/{id}").authenticated()
            .requestMatchers(HttpMethod.GET, "/postings").permitAll()

            .anyRequest().denyAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))

            .and()
            .apply(FilterConfig(tokenProvider, objectMapper))
            .and()
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()
}