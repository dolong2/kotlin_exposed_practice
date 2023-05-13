package com.practice.exposed.domain.member.service.impl

import com.practice.exposed.domain.member.domain.persistence.RefreshToken
import com.practice.exposed.domain.member.exception.MemberNotFoundException
import com.practice.exposed.domain.member.persenation.response.RefreshResponse
import com.practice.exposed.domain.member.repository.MemberRepository
import com.practice.exposed.domain.member.repository.RefreshTokenRepository
import com.practice.exposed.domain.member.service.UpdateRefreshTokenService
import com.practice.exposed.global.config.jwt.TokenProvider
import com.practice.exposed.global.config.jwt.exception.InvalidTokenException
import org.springframework.stereotype.Service

@Service
class UpdateRefreshTokenServiceImpl(
    private val memberRepository: MemberRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenProvider: TokenProvider
) : UpdateRefreshTokenService{
    override fun execute(refreshToken: String): RefreshResponse {
        val parseToken = tokenProvider.parseToken(refreshToken)
            ?: throw InvalidTokenException()
        val email = tokenProvider.exactEmailFromRefreshToken(parseToken)
        val roles = tokenProvider.exactRoleFromRefreshToken(parseToken)
        if (!memberRepository.existsByEmail(email))
            throw MemberNotFoundException()
        refreshTokenRepository.deleteById(parseToken)
        val accessToken = tokenProvider.generateAccessToken(email, roles)
        val newRefreshToken = tokenProvider.generateRefreshToken(email, roles)
        val accessExpiredTime = tokenProvider.accessExpiredTime
        refreshTokenRepository.save(RefreshToken(token = newRefreshToken, userEmail = email))
        return RefreshResponse(accessToken = accessToken, refreshToken = newRefreshToken, accessExpires = accessExpiredTime)
    }
}