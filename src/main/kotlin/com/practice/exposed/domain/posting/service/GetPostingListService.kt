package com.practice.exposed.domain.posting.service

import com.practice.exposed.domain.posting.presentation.response.PostingListResDto

interface GetPostingListService {
    fun execute(): PostingListResDto
}