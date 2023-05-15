package com.practice.exposed.domain.posting.domain.entity

import com.practice.exposed.domain.posting.domain.persistence.PostingTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.javatime.date

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
}