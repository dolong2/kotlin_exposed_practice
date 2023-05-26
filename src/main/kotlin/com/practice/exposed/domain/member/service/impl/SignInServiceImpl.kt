package com.practice.exposed.domain.member.service.impl

import com.practice.exposed.domain.member.domain.persistence.RefreshToken
import com.practice.exposed.domain.member.exception.MemberNotFoundException
import com.practice.exposed.domain.member.exception.PasswordMismatchException
import com.practice.exposed.domain.member.persenation.request.SignInRequest
import com.practice.exposed.domain.member.persenation.response.SignInResponse
import com.practice.exposed.domain.member.repository.MemberRepository
import com.practice.exposed.domain.member.repository.RefreshTokenRepository
import com.practice.exposed.domain.member.repository.RoleRepository
import com.practice.exposed.domain.member.service.SignInService
import com.practice.exposed.global.security.jwt.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignInServiceImpl(
    private val memberRepository: MemberRepository,
    private val roleRepository: RoleRepository,
    private val tokenProvider: TokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository,
) : SignInService{
    override fun execute(signInRequest: SignInRequest): SignInResponse {
        val member = (memberRepository.findByEmail(signInRequest.email)
            ?: throw MemberNotFoundException())
        if (!passwordEncoder.matches(signInRequest.password, member.password))
            throw PasswordMismatchException()
        val roles = roleRepository.findByMember(member)
        val accessToken = tokenProvider.generateAccessToken(member.email, roles.map { it.role })
        val refreshToken = tokenProvider.generateRefreshToken(member.email, roles.map { it.role })
        refreshTokenRepository.save(RefreshToken(userEmail = member.email, token = refreshToken))
        return SignInResponse(accessToken = accessToken, refreshToken = refreshToken, accessExpires = tokenProvider.accessExpiredTime)
    }
}
