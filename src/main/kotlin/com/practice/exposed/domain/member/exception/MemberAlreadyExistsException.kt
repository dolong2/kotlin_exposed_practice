package com.practice.exposed.domain.member.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class MemberAlreadyExistsException : BasicException(ErrorCode.DUPLICATE_MEMBER) {
}