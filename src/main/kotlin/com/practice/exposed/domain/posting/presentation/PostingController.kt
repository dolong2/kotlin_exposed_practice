package com.practice.exposed.domain.posting.presentation

import com.practice.exposed.domain.posting.presentation.request.CreatePostingRequest
import com.practice.exposed.domain.posting.service.CreatePostingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/postings")
class PostingController(
    private val createPostingService: CreatePostingService
) {
    @PostMapping("/one")
    fun writePosting(@RequestBody createPostingRequest: CreatePostingRequest): ResponseEntity<Void> =
        createPostingService.execute(createPostingRequest)
            .run { ResponseEntity(HttpStatus.CREATED) }

    @PostMapping
    fun writePostings(@RequestBody createPostingRequests: List<CreatePostingRequest>): ResponseEntity<Void> =
        createPostingService.execute(createPostingRequests)
            .run { ResponseEntity(HttpStatus.CREATED) }
}