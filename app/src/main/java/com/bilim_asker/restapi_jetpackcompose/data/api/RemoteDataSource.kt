package com.bilim_asker.restapi_jetpackcompose.data.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postServices: PostServices) {

    suspend fun getAllPosts() = postServices.getAllPost()
    suspend fun postPosts(body: PostResponseModel) = postServices.postPost(body = body)
    suspend fun putPosts(id: String, body: PostResponseModel) =
        postServices.putPost(id = id, body = body)

    suspend fun patchPosts(id: String, body: PostResponseModel) =
        postServices.patchPost(id = id, body = body)

    suspend fun deletePosts(id: String) = postServices.deletePost(id = id)

    fun getPagingAllPost() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PostPagingDataSource(postServices = postServices) }
    ).flow
}