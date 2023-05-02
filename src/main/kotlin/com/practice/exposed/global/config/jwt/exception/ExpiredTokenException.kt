package com.practice.exposed.global.config.jwt.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class ExpiredTokenException : BasicException(ErrorCode.EXPIRED_TOKEN) {
}