package com.practice.exposed.domain.posting.service.impl

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.posting.domain.entity.Posting
import com.practice.exposed.domain.posting.exception.NotEqualWriterException
import com.practice.exposed.domain.posting.exception.PostingNotFoundException
import com.practice.exposed.domain.posting.repository.PostingRepository
import com.practice.exposed.domain.posting.service.DeletePostingService
import com.practice.exposed.global.util.UserUtil
import org.springframework.stereotype.Service

@Service
class DeletePostingServiceImpl(
    private val userUtil: UserUtil,
    private val postingRepository: PostingRepository
) : DeletePostingService {
    override fun execute(id: Long) {
        val member = userUtil.fetchCurrentUser()
        val posting = (postingRepository.findById(id)
            ?: throw PostingNotFoundException())
        if (isNotWriter(member, posting))
            throw NotEqualWriterException()
        postingRepository.delete(id)
    }

    private fun isNotWriter(
        member: Member,
        posting: Posting,
    ) = member.id.value != posting.writer.value
}