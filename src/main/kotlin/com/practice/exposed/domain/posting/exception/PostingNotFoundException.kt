package com.practice.exposed.domain.posting.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class PostingNotFoundException : BasicException(ErrorCode.POSTING_NOT_FOUND) {
}