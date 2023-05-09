package com.practice.exposed.global.config.jwt

import com.practice.exposed.domain.member.enums.RoleEnum
import com.practice.exposed.global.config.jwt.exception.ExpiredTokenException
import com.practice.exposed.global.config.jwt.exception.InvalidTokenException
import com.practice.exposed.global.config.jwt.properties.JwtProperty
import com.practice.exposed.global.config.jwt.properties.TokenTimeProperties
import com.practice.exposed.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.time.ZonedDateTime
import java.util.*

@Component
class TokenProvider(
    private val jwtProperty: JwtProperty,
    private val tokenTimeProperties: TokenTimeProperties,
    private val authDetailsService: AuthDetailsService,
) {
    companion object {
        const val ACCESS_TYPE = "access"
        const val REFRESH_TYPE = "refresh"
        const val TOKEN_PREFIX = "Bearer "
        const val AUTHORITY = "authority"
    }

    val accessExpiredTime: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(tokenTimeProperties.accessTime)

    val refreshExpiredTime: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(tokenTimeProperties.refreshTime)

    fun generateAccessToken(email: String, roles: List<RoleEnum>): String =
        generateToken(email, ACCESS_TYPE, jwtProperty.accessSecret, tokenTimeProperties.accessTime, roles)

    fun generateRefreshToken(email: String, roles: List<RoleEnum>): String =
        generateToken(email, REFRESH_TYPE, jwtProperty.refreshSecret, tokenTimeProperties.refreshTime, roles)

    fun resolveToken(req: HttpServletRequest): String? {
        val token = req.getHeader("Authorization") ?: return null
        return parseToken(token)
    }

    fun exactEmailFromRefreshToken(refresh: String): String {
        return getTokenSubject(refresh, jwtProperty.refreshSecret)
    }

    fun exactRoleFromRefreshToken(refresh: String): RoleEnum {
        return when (getTokenBody(refresh, jwtProperty.refreshSecret).get(AUTHORITY, String::class.java)) {
            "ROLE_STUDENT" -> RoleEnum.ROLE_MEMBER
            "ROLE_ADMIN" -> RoleEnum.ROLE_ADMIN
            else -> throw RuntimeException()
        }

    }

    fun exactTypeFromRefreshToken(refresh: String): String =
        getTokenSubject(refresh, jwtProperty.refreshSecret)

    fun authentication(token: String): Authentication {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token, jwtProperty.accessSecret))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun parseToken(token: String): String? =
        if (token.startsWith(TOKEN_PREFIX)) token.replace(TOKEN_PREFIX, "") else null

    private fun generateToken(email: String, type: String, secret: Key, exp: Long, roles: List<RoleEnum>): String {
        val claims = Jwts.claims().setSubject(email)
        claims["type"] = type
        claims[AUTHORITY] = roles
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .signWith(secret, SignatureAlgorithm.HS256)
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
    }

    private fun getTokenBody(token: String, secret: Key): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    private fun getTokenSubject(token: String, secret: Key): String =
        getTokenBody(token, secret).subject
}