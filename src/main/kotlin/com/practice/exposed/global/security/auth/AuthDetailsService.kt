package com.practice.exposed.global.security.auth

import com.practice.exposed.domain.member.exception.MemberNotFoundException
import com.practice.exposed.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        AuthDetails((memberRepository.findByEmail(username) ?: throw MemberNotFoundException()))
}