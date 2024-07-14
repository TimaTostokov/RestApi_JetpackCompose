package com.bilim_asker.restapi_jetpackcompose.data.repository

import com.bilim_asker.restapi_jetpackcompose.data.api.RemoteDataSource
import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel
import com.bilim_asker.restapi_jetpackcompose.utils.BaseApiResponse
import com.bilim_asker.restapi_jetpackcompose.utils.NetworkResult
import javax.inject.Inject

class PostRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

    suspend fun getAllPosts(): NetworkResult<List<PostResponseModel>> {
        return safeApiCall { remoteDataSource.getAllPosts() }
    }

    suspend fun postPost(body: PostResponseModel): NetworkResult<PostResponseModel> {
        return safeApiCall { remoteDataSource.postPosts(body = body) }
    }

    suspend fun putPost(id: String, body: PostResponseModel): NetworkResult<PostResponseModel> {
        return safeApiCall { remoteDataSource.putPosts(id = id, body = body) }
    }

    suspend fun patchPost(id: String, body: PostResponseModel): NetworkResult<PostResponseModel> {
        return safeApiCall { remoteDataSource.patchPosts(id = id, body = body) }
    }

    suspend fun deletePost(id: String): NetworkResult<PostResponseModel> {
        return safeApiCall { remoteDataSource.deletePosts(id = id) }
    }

    fun getPagingSource() = remoteDataSource.getPagingAllPost()

}