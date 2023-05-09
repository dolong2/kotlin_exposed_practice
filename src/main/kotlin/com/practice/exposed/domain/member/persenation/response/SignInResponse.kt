package com.practice.exposed.domain.member.persenation.response

import java.time.ZonedDateTime

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExpires: ZonedDateTime
)