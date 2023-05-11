package com.practice.exposed.domain.member.repository

import com.practice.exposed.domain.member.domain.persistence.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
}