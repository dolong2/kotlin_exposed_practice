package com.practice.exposed.domain.member.enums

import org.springframework.security.core.GrantedAuthority

enum class RoleEnum(description: String) : GrantedAuthority {
    ROLE_MEMBER("유저"),
    ROLE_ADMIN("관리");

    override fun getAuthority(): String = name
}