package com.practice.exposed.domain.posting.domain.persistence

import com.practice.exposed.domain.member.domain.persistence.MemberTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date

object PostingTable : LongIdTable(name = "Posting", columnName = "id") {
    val title = varchar("title", 255)
    val content = varchar("content", 400)
    val createdDate = date("created_date")
    val lastModifiedDate = date("updated_date")
    val writer = reference("writer_id", MemberTable)
}