package com.practice.exposed.domain.member.repository.impl

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.domain.entity.Role
import com.practice.exposed.domain.member.domain.persistence.MemberTable
import com.practice.exposed.domain.member.domain.persistence.RoleTable
import com.practice.exposed.domain.member.enums.RoleEnum
import com.practice.exposed.domain.member.repository.RoleRepository
import com.practice.exposed.global.annotation.exposed.ExposedTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository

@Repository
class RoleRepositoryImpl : RoleRepository {
    @ExposedTransaction(target = [RoleTable::class])
    override fun findByMember(member: Member): List<Role> =
        Role.find(RoleTable.member.eq(member.id)).toList()

    @ExposedTransaction(target = [MemberTable::class, RoleTable::class])
    override fun saveRole(role: RoleEnum, member: Member): Role =
        Role.new { createRole(role, member) }
}