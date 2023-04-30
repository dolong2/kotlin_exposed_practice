package com.practice.exposed.domain.member.domain.persistence

import org.jetbrains.exposed.dao.id.LongIdTable

object MemberTable : LongIdTable(name = "member", columnName = "id") {
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val name = varchar("name", 255)
}