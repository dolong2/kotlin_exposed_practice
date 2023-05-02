package com.practice.exposed.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.exposed.global.exception.ErrorCode
import com.practice.exposed.global.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.error("==========Access Denied==========")
        val errorCode = ErrorCode.UNAUTHORIZED
        val responseString = objectMapper.writeValueAsString(ErrorResponse(errorCode.msg, errorCode.code))
        response.characterEncoding = "UTF-8"
        response.status = errorCode.code
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(responseString)
    }
}