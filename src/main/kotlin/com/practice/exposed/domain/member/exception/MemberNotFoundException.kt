package com.practice.exposed.domain.member.exception

import com.practice.exposed.global.exception.BasicException
import com.practice.exposed.global.exception.ErrorCode

class MemberNotFoundException : BasicException(ErrorCode.MEMBER_NOT_FOUND)