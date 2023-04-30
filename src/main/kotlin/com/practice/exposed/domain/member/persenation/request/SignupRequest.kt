package com.practice.exposed.domain.member.persenation.request

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
)