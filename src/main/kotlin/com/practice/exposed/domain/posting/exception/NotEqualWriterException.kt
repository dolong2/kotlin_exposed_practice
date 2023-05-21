package com.practice.exposed.domain.posting.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class NotEqualWriterException : BasicException(ErrorCode.NOT_SAME_WRITER) {
}