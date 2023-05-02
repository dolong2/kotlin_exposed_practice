package com.practice.exposed.domain.member.domain.entity

import com.practice.exposed.domain.member.domain.persistence.RoleTable
import com.practice.exposed.domain.member.enums.RoleEnum
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Role(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Role>(RoleTable)

    var member by RoleTable.member
        private set
    var role by RoleTable.roleEnum
        private set

    fun createRole(role: RoleEnum, member: Member){
        this.member = member.id
        this.role = role
    }
}