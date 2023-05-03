package com.practice.exposed.domain.member.repository

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.persenation.request.SignupRequest

interface MemberRepository {
    fun findByEmail(email: String): Member?
    fun save(signupRequest: SignupRequest): Member
    fun existsByEmail(email: String): Boolean
}