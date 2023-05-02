package com.practice.exposed.global.exception

open class BasicException (
    val errorCode: ErrorCode
): RuntimeException(errorCode.msg)