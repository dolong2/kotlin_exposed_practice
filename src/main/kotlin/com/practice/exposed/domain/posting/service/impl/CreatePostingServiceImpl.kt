package com.practice.exposed.domain.posting.service.impl

import com.practice.exposed.domain.posting.domain.entity.Posting
import com.practice.exposed.domain.posting.presentation.request.CreatePostingRequest
import com.practice.exposed.domain.posting.repository.PostingRepository
import com.practice.exposed.domain.posting.repository.dao.PostingDao
import com.practice.exposed.domain.posting.service.CreatePostingService
import com.practice.exposed.global.util.UserUtil
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CreatePostingServiceImpl(
    private val postingRepository: PostingRepository,
    private val userUtil: UserUtil,
) : CreatePostingService{
    override fun execute(createPostingRequest: CreatePostingRequest) {
        postingRepository.save(createPostingRequest.toPostingDao())
    }

    override fun execute(createPostingRequests: List<CreatePostingRequest>) {
        postingRepository.saveAll(createPostingRequests.map { it.toPostingDao() })
    }

    private fun CreatePostingRequest.toPostingDao(): PostingDao =
        PostingDao(
            title = this.title,
            content = this.content,
            createdDate = LocalDate.now(),
            updatedDate = LocalDate.now(),
            writer = userUtil.fetchCurrentUser()
        )
}