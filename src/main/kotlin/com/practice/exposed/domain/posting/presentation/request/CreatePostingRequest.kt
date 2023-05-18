package com.practice.exposed.domain.posting.presentation.request

data class CreatePostingRequest (
    val title: String,
    val content: String,
)