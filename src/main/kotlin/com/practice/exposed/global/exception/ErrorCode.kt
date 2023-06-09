package com.practice.exposed.global.exception

enum class ErrorCode(
    val code: Int,
    val msg: String
) {
    //400
    BAD_REQUEST(400, "올바르지 않은 요청"),
    MISMATCH_PASSWORD(400, "올바르지 않은 패스워드"),

    //401
    UNAUTHORIZED(401, "권한이 없음"),
    EXPIRED_TOKEN(401, "토큰이 만료됨"),

    //403
    FORBIDDEN(403, "요청이 금지됨"),
    NOT_UPDATE_POSTING(403, "게시물을 수정할 수 없음"),
    NOT_DELETE_POSTING(403, "게시물을 삭제할 수 없음"),

    //404
    NOT_FOUND(404, "리소스를 찾을 수 없음"),
    MEMBER_NOT_FOUND(404, "해당 유저를 찾을 수 없음"),
    POSTING_NOT_FOUND(404, "해당 게시물을 찾을 수 없음"),

    //409
    DUPLICATE_MEMBER(409, "이미 해당 유저가 존재함"),

    //500
    INTERNAL_ERROR(500, "알 수 없는 에러")
}