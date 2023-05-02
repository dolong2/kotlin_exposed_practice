package com.practice.exposed.global.exception.handler

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BasicExceptionHandler {
    private val log: Logger = LoggerFactory.getLogger(this::class.simpleName)

    @ExceptionHandler(BasicException::class)
    fun handleBasicException(request: HttpServletRequest, ex: BasicException): ResponseEntity<ErrorResponse>{
        log.error(request.requestURI)
        log.error(ex.message)
        val errorResponse = ErrorResponse(ex.errorCode)
        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(ex.errorCode.code))
    }
}