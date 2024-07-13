package com.bilim_asker.restapi_jetpackcompose.domain.usecases

import com.bilim_asker.restapi_jetpackcompose.data.repository.PostRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke(id: String) = postRepository.deletePost(id = id)
}