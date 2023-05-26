package com.practice.exposed.global.security.jwt.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class InvalidTokenException : BasicException(
    ErrorCode.UNAUTHORIZED
)