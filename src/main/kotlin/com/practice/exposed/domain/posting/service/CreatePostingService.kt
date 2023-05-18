package com.practice.exposed.domain.posting.service

import com.practice.exposed.domain.posting.presentation.request.CreatePostingRequest

interface CreatePostingService {
    fun execute(createPostingRequest: CreatePostingRequest)
    fun execute(createPostingRequests: List<CreatePostingRequest>)
}