package com.practice.exposed.domain.member.domain.entity

import com.practice.exposed.domain.member.domain.persistence.MemberTable
import com.practice.exposed.domain.member.persenation.request.SignupRequest
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Member(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Member>(MemberTable)
    var name by MemberTable.name
    private set
    var email by MemberTable.email
    private set
    var password by MemberTable.password
    private set

    fun signup(request: SignupRequest){
        name = request.name
        email = request.email
        password = request.password
    }
}