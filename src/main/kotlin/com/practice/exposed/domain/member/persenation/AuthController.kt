package com.practice.exposed.domain.member.persenation

import com.practice.exposed.domain.member.persenation.request.SignupRequest
import com.practice.exposed.domain.member.service.SignupService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signupService: SignupService
) {
    @PostMapping
    fun createMember(@RequestBody signupRequest: SignupRequest): ResponseEntity<Void> {
        signupService.execute(signupRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }
}