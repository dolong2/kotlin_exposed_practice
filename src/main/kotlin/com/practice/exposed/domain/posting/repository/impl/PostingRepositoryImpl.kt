package com.practice.exposed.domain.posting.repository.impl

import com.practice.exposed.domain.posting.domain.entity.Posting
import com.practice.exposed.domain.posting.domain.persistence.PostingTable
import com.practice.exposed.domain.posting.repository.PostingRepository
import com.practice.exposed.domain.posting.repository.dao.PostingDao
import com.practice.exposed.global.annotation.exposed.ExposedTransaction
import org.jetbrains.exposed.sql.batchInsert
import org.springframework.stereotype.Repository

@Repository
class PostingRepositoryImpl : PostingRepository {

    @ExposedTransaction(target = [PostingTable::class])
    override fun save(postingDao: PostingDao): Posting =
        Posting.new{ save(postingDao) }

    @ExposedTransaction(target = [PostingTable::class])
    override fun saveAll(postingDao: List<PostingDao>) {
        PostingTable.batchInsert(
            data = postingDao,
            ignore = false,
            shouldReturnGeneratedValues = false
        ){
            this[PostingTable.title] = it.title
            this[PostingTable.content] = it.content
            this[PostingTable.createdDate] = it.createdDate
            this[PostingTable.lastModifiedDate] = it.updatedDate
            this[PostingTable.writer] = it.writer.id
        }
    }

}