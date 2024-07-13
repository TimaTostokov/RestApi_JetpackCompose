package com.bilim_asker.restapi_jetpackcompose.data.api

import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostServices {

    @GET("/posts")
    suspend fun getAllPost(): Response<List<PostResponseModel>>

    @POST("/posts")
    suspend fun postPost(@Body body: PostResponseModel): Response<PostResponseModel>

    @PUT("/posts/{id")
    suspend fun putPost(
        @Path("id") id: String,
        @Body body: PostResponseModel
    ): Response<PostResponseModel>

    @PATCH("/posts/{id}")
    suspend fun patchPost(
        @Path("id") id: String,
        @Body body: PostResponseModel
    ): Response<PostResponseModel>

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: String): Response<PostResponseModel>
}