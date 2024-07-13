package com.bilim_asker.restapi_jetpackcompose.domain.usecases

import com.bilim_asker.restapi_jetpackcompose.data.repository.PostRepository
import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel
import javax.inject.Inject

class PostPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke(body: PostResponseModel) = postRepository.postPost(body = body)
}