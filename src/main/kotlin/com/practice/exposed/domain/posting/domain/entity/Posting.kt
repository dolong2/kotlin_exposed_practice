package com.practice.exposed.domain.posting.domain.entity

import com.practice.exposed.domain.posting.domain.persistence.PostingTable
import com.practice.exposed.domain.posting.repository.dao.PostingDao
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Posting(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Posting>(PostingTable)

    var title by PostingTable.title
        private set
    var content by PostingTable.content
        private set
    var createdDate by PostingTable.createdDate
        private set
    var lastModifiedDate by PostingTable.lastModifiedDate
        private set
    var writer by PostingTable.writer
        private set

    fun save(postingDao: PostingDao) {
        this.title = postingDao.title
        this.content = postingDao.content
        this.createdDate = postingDao.createdDate
        this.lastModifiedDate = postingDao.updatedDate
        this.writer = postingDao.writer.id
    }
}