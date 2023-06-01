package com.practice.exposed.domain.posting.service.impl

import com.practice.exposed.domain.posting.presentation.response.PostingListResDto
import com.practice.exposed.domain.posting.presentation.response.PostingResDto
import com.practice.exposed.domain.posting.repository.PostingRepository
import com.practice.exposed.domain.posting.service.GetPostingListService
import org.springframework.stereotype.Service

@Service
class GetPostingListServiceImpl(
    private val postingRepository: PostingRepository
): GetPostingListService {
    override fun execute(): PostingListResDto =
        postingRepository.findAll()
            .map { PostingResDto(it) }
            .let { PostingListResDto(it) }
}