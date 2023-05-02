package com.practice.exposed.global.exception

class ErrorResponse(
    val msg: String,
    val code: Int
) {
    constructor(errorCode: ErrorCode) : this(errorCode.msg, errorCode.code)
}