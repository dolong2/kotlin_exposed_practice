package com.practice.exposed.domain.member.repository.impl

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.domain.persistence.MemberTable
import com.practice.exposed.domain.member.persenation.request.SignupRequest
import com.practice.exposed.domain.member.repository.MemberRepository
import com.practice.exposed.global.annotation.exposed.ExposedTransaction
import org.jetbrains.exposed.sql.insert
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl : MemberRepository {
    @ExposedTransaction(target = [MemberTable::class])
    override fun findByEmail(email: String): Member? =
        Member.find { MemberTable.email.eq(email) }.firstOrNull()

    @ExposedTransaction(target = [MemberTable::class])
    override fun save(signupRequest: SignupRequest): Member {
        return Member.new { signup(signupRequest) }
    }

    @ExposedTransaction(target = [MemberTable::class])
    override fun existsByEmail(email: String): Boolean =
        !Member.find { MemberTable.email.eq(email) }.empty()


}