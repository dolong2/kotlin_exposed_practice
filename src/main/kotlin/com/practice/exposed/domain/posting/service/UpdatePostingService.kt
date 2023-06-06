package com.practice.exposed.domain.posting.service

import com.practice.exposed.domain.posting.presentation.request.UpdatePostingRequest

interface UpdatePostingService {
    fun execute(id: Long, updatePostingRequest: UpdatePostingRequest)
}