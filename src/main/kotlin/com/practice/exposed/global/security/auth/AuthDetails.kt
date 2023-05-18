package com.practice.exposed.global.security.auth

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.member.domain.entity.Role
import com.practice.exposed.domain.member.domain.persistence.RoleTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val member: Member
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? =
        transaction { Role.find{ RoleTable.member.eq(member.id) }.map { it.role }.toMutableList() }

    override fun getPassword(): String? = null

    override fun getUsername(): String = member.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}