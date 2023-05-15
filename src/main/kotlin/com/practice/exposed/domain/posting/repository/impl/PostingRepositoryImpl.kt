package com.practice.exposed.domain.posting.repository.impl

import com.practice.exposed.domain.posting.domain.entity.Posting
import com.practice.exposed.domain.posting.repository.PostingRepository
import com.practice.exposed.domain.posting.repository.dao.PostingDao
import org.springframework.stereotype.Repository

@Repository
class PostingRepositoryImpl : PostingRepository {
    override fun save(postingDao: PostingDao): Posting =
        Posting.new{ save(postingDao) }

}