package com.practice.exposed.domain.member.service.impl

import com.practice.exposed.domain.member.enums.RoleEnum
import com.practice.exposed.domain.member.exception.MemberAlreadyExistsException
import com.practice.exposed.domain.member.persenation.request.SignupRequest
import com.practice.exposed.domain.member.repository.MemberRepository
import com.practice.exposed.domain.member.repository.RoleRepository
import com.practice.exposed.domain.member.repository.dao.MemberDao
import com.practice.exposed.domain.member.service.SignupService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignupServiceImpl(
    private val memberRepository: MemberRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : SignupService{
    override fun execute(signupRequest: SignupRequest) {
        if (memberRepository.existsByEmail(signupRequest.email))
            throw MemberAlreadyExistsException()
        val member = memberRepository.save(signupRequest.toMemberDao())
        roleRepository.saveRole(RoleEnum.ROLE_MEMBER, member)
    }
    
    private fun SignupRequest.toMemberDao(): MemberDao =
        MemberDao(
            email = this.email,
            name = this.name,
            password = passwordEncoder.encode(this.password)
        )
}
