package com.practice.exposed.domain.member.persenation

import com.practice.exposed.domain.member.persenation.request.SignInRequest
import com.practice.exposed.domain.member.persenation.request.SignupRequest
import com.practice.exposed.domain.member.persenation.response.RefreshResponse
import com.practice.exposed.domain.member.persenation.response.SignInResponse
import com.practice.exposed.domain.member.service.SignInService
import com.practice.exposed.domain.member.service.SignupService
import com.practice.exposed.domain.member.service.UpdateRefreshTokenService
import com.practice.exposed.global.annotation.presentation.ApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@ApiController("/auth")
class AuthController(
    private val signupService: SignupService,
    private val signInService: SignInService,
    private val updateRefreshTokenService: UpdateRefreshTokenService
) {
    @PostMapping("/signup")
    fun createMember(@RequestBody signupRequest: SignupRequest): ResponseEntity<Void> {
        signupService.execute(signupRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping
    fun signInMember(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> =
        signInService.execute(signInRequest)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun refreshToken(@RequestHeader refreshToken: String): ResponseEntity<RefreshResponse> =
        updateRefreshTokenService.execute(refreshToken)
            .let { ResponseEntity.ok(it) }
}