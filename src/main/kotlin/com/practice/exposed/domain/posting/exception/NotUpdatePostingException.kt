package com.practice.exposed.domain.posting.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class NotUpdatePostingException : BasicException(ErrorCode.NOT_UPDATE_POSTING) {
}