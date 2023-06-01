package com.practice.exposed.domain.posting.presentation.response

import com.practice.exposed.domain.posting.domain.entity.Posting
import java.time.LocalDate

data class PostingResDto(
    val id: Long,
    val title: String,
    val content: String,
    val createdDate: LocalDate,
    val lastModifiedDate: LocalDate,
    val writer: Long,
) {
    constructor(posting: Posting): this(posting.id.value, posting.title, posting.content, posting.createdDate, posting.lastModifiedDate, posting.writer.value)
}
