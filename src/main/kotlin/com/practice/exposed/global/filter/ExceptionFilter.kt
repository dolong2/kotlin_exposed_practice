package com.practice.exposed.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode
import com.practice.exposed.global.exception.ErrorResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BasicException) {
            log.error(e.message)
            sendError(response, e.errorCode)
        } catch (e: Exception) {
            log.error(e.message)
            response.characterEncoding = "UTF-8"
            sendError(response, ErrorCode.INTERNAL_ERROR)
        }
    }

    private fun sendError(res: HttpServletResponse, errorCode: ErrorCode) {
        val errorResponse = ErrorResponse(errorCode)
        val responseString = objectMapper!!.writeValueAsString(errorResponse)
        res.status = errorCode.code
        res.contentType = MediaType.APPLICATION_JSON_VALUE
        res.writer.write(responseString)
    }
}