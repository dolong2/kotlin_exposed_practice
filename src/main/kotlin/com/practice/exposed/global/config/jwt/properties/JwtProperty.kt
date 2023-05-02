package com.practice.exposed.global.config.jwt.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.Key

@ConfigurationProperties(prefix = "jwt")
class JwtProperty(
    accessSecret: String,
    refreshSecret: String
) {
    val accessSecret: Key
    val refreshSecret: Key

    init {
        this.accessSecret = Keys.hmacShaKeyFor(accessSecret.toByteArray())
        this.refreshSecret = Keys.hmacShaKeyFor(refreshSecret.toByteArray())
    }
}