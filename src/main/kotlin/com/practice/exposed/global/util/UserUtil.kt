package com.practice.exposed.global.util

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.exception.MemberNotFoundException
import com.practice.exposed.domain.member.repository.MemberRepository
import com.practice.exposed.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserUtil(
    private val memberRepository: MemberRepository
) {
    fun fetchCurrentUser(): Member {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val email = if (principal is UserDetails) {
            (principal as AuthDetails).username
        } else {
            principal.toString()
        }
        return fetchUserByEmail(email)
    }

    fun fetchUserByEmail(email: String): Member =
        memberRepository.findByEmail(email) ?: throw MemberNotFoundException()
}