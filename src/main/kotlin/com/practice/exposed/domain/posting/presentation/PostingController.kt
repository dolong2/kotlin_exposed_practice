package com.practice.exposed.domain.posting.presentation

import com.practice.exposed.domain.posting.presentation.request.CreatePostingRequest
import com.practice.exposed.domain.posting.presentation.request.UpdatePostingRequest
import com.practice.exposed.domain.posting.presentation.response.PostingListResDto
import com.practice.exposed.domain.posting.service.CreatePostingService
import com.practice.exposed.domain.posting.service.DeletePostingService
import com.practice.exposed.domain.posting.service.GetPostingListService
import com.practice.exposed.domain.posting.service.UpdatePostingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/postings")
class PostingController(
    private val createPostingService: CreatePostingService,
    private val deletePostingService: DeletePostingService,
    private val getPostingListService: GetPostingListService,
    private val updatePostingService: UpdatePostingService
) {
    @PostMapping("/one")
    fun writePosting(@RequestBody createPostingRequest: CreatePostingRequest): ResponseEntity<Void> =
        createPostingService.execute(createPostingRequest)
            .run { ResponseEntity(HttpStatus.CREATED) }

    @PostMapping
    fun writePostings(@RequestBody createPostingRequests: List<CreatePostingRequest>): ResponseEntity<Void> =
        createPostingService.execute(createPostingRequests)
            .run { ResponseEntity(HttpStatus.CREATED) }

    @DeleteMapping("/{id}")
    fun deletePosting(@PathVariable id: Long): ResponseEntity<Void> =
        deletePostingService.execute(id)
            .run { ResponseEntity.ok().build() }

    @GetMapping
    fun getAllPosting(): ResponseEntity<PostingListResDto> =
        getPostingListService.execute()
            .run { ResponseEntity.ok(this) }

    @PatchMapping("/{id}")
    fun updatePosting(@PathVariable id: Long, @RequestBody updatePostingRequest: UpdatePostingRequest): ResponseEntity<Void> =
        updatePostingService.execute(id, updatePostingRequest)
            .run { ResponseEntity.ok().build() }
}