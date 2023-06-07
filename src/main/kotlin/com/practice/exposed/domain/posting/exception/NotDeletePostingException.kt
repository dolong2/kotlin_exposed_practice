package com.practice.exposed.domain.posting.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class NotDeletePostingException : BasicException(ErrorCode.NOT_DELETE_POSTING) {
}