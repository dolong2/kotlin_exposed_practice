package com.practice.exposed.domain.posting.service.impl

import com.practice.exposed.domain.member.domain.entity.Member
import com.practice.exposed.domain.posting.domain.entity.Posting
import com.practice.exposed.domain.posting.exception.NotUpdatePostingException
import com.practice.exposed.domain.posting.exception.PostingNotFoundException
import com.practice.exposed.domain.posting.presentation.request.UpdatePostingRequest
import com.practice.exposed.domain.posting.repository.PostingRepository
import com.practice.exposed.domain.posting.repository.dao.PostingDao
import com.practice.exposed.domain.posting.service.UpdatePostingService
import com.practice.exposed.global.util.UserUtil
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UpdatePostingServiceImpl(
    private val postingRepository: PostingRepository,
    private val userUtil: UserUtil,
) : UpdatePostingService {
    override fun execute(id: Long, updatePostingRequest: UpdatePostingRequest) {
        val posting = (postingRepository.findById(id)
            ?: throw PostingNotFoundException())
        val currentUser = userUtil.fetchCurrentUser()
        if (isNotWriter(posting, currentUser) && !userUtil.isAdmin(currentUser))
            throw NotUpdatePostingException()
        val postingDao = updatePostingRequest.toPostingDao(writer = currentUser, createdDate = posting.createdDate)
        postingRepository.update(id, postingDao)
    }

    private fun isNotWriter(
        posting: Posting,
        currentUser: Member,
    ) = posting.writer != currentUser.id

    private fun UpdatePostingRequest.toPostingDao(
        writer: Member,
        createdDate: LocalDate
    ): PostingDao =
        PostingDao(
            title = this.title,
            content = this.content,
            createdDate = createdDate,
            updatedDate = LocalDate.now(),
            writer = writer
        )
}