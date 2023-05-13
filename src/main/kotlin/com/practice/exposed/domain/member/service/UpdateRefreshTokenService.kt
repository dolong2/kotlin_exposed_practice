package com.practice.exposed.domain.member.service

import com.practice.exposed.domain.member.persenation.response.RefreshResponse

interface UpdateRefreshTokenService {
    fun execute(refreshToken: String): RefreshResponse
}