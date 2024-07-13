package com.bilim_asker.restapi_jetpackcompose.domain.usecases

import com.bilim_asker.restapi_jetpackcompose.data.repository.PostRepository
import javax.inject.Inject

class GetAllPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke() = postRepository.getAllPosts()
}