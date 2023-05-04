package com.practice.exposed.domain.member.service

import com.practice.exposed.domain.member.persenation.request.SignupRequest

interface SignupService {
    fun execute(signupRequest: SignupRequest)
}