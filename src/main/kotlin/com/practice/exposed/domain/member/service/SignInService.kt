package com.practice.exposed.domain.member.service

import com.practice.exposed.domain.member.persenation.request.SignInRequest
import com.practice.exposed.domain.member.persenation.response.SignInResponse

interface SignInService {
    fun execute(signInRequest: SignInRequest): SignInResponse
}