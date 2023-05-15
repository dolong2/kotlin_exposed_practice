package com.practice.exposed.domain.posting.repository.dao

import com.practice.exposed.domain.member.domain.entity.Member
import java.time.LocalDate

data class PostingDao(
    val title: String,
    val content: String,
    val createdDate: LocalDate,
    val updatedDate: LocalDate,
    val writer: Member
)