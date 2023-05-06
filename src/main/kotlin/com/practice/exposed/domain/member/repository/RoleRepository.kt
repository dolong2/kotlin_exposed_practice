package com.practice.exposed.domain.member.repository

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.domain.entity.Role
import com.practice.exposed.domain.member.enums.RoleEnum

interface RoleRepository {
    fun findByMember(member: Member): List<Role>
    fun saveRole(role: RoleEnum, member: Member): Role
}