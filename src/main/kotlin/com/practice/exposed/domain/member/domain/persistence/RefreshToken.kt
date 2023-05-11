package com.practice.exposed.domain.member.domain.persistence

import com.practice.exposed.global.config.jwt.properties.TokenTimeProperties
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "refreshToken", timeToLive = 604800)
class RefreshToken(
    @Id
    @Indexed
    val token: String,
    @Indexed
    val userId: Long,
)