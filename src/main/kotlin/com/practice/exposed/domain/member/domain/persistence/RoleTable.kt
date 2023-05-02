package com.practice.exposed.domain.member.domain.persistence

import com.practice.exposed.domain.member.enums.RoleEnum
import org.jetbrains.exposed.dao.id.LongIdTable

object RoleTable : LongIdTable("role") {
    val member = reference("member_id", MemberTable)
    val roleEnum = enumerationByName("role", 50, RoleEnum::class)
}