package com.practice.exposed.global.util

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.enums.RoleEnum
import com.practice.exposed.domain.member.exception.MemberNotFoundException
import com.practice.exposed.domain.member.repository.MemberRepository
import com.practice.exposed.domain.member.repository.RoleRepository
import com.practice.exposed.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserUtil(
    private val memberRepository: MemberRepository,
    private val roleRepository: RoleRepository
) {
    fun fetchCurrentUser(): Member {
        val email = getCurrentUserEmail()
        return fetchUserByEmail(email)
    }

    fun fetchUserByEmail(email: String): Member =
        memberRepository.findByEmail(email) ?: throw MemberNotFoundException()

    fun isAdmin(): Boolean =
        roleRepository.findByMember(fetchCurrentUser())
            .map { it.role }
            .contains(RoleEnum.ROLE_ADMIN)

    fun isAdmin(member: Member): Boolean =
        roleRepository.findByMember(member)
            .map { it.role }
            .contains(RoleEnum.ROLE_ADMIN)

    private fun getCurrentUserEmail(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val email = if (principal is UserDetails) {
            (principal as AuthDetails).username
        } else {
            principal.toString()
        }
        return email
    }
}