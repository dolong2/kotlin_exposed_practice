package com.practice.exposed.domain.member.persenation.response

import java.time.ZonedDateTime

data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExpires: ZonedDateTime
)
