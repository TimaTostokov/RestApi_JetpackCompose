package com.bilim_asker.restapi_jetpackcompose.data.api.model

import com.google.gson.annotations.SerializedName

data class PostResponseModel(
    @SerializedName("userId")
    val userId: Long? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)