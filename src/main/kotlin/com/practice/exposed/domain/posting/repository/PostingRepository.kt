package com.practice.exposed.domain.posting.repository

import com.practice.exposed.domain.posting.domain.entity.Posting
import com.practice.exposed.domain.posting.repository.dao.PostingDao

interface PostingRepository {
    fun save(postingDao: PostingDao): Posting

    fun saveAll(postingDao: List<PostingDao>)

    fun findById(id: Long): Posting?

    fun delete(id: Long)
}