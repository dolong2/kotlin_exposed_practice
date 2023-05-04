package com.practice.exposed.domain.member.repository

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.repository.dao.MemberDao

interface MemberRepository {
    fun findByEmail(email: String): Member?
    fun save(memberDao: MemberDao): Member
    fun existsByEmail(email: String): Boolean
}