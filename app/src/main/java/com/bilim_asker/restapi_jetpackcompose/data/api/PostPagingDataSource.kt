package com.bilim_asker.restapi_jetpackcompose.data.api

import android.util.Log
import androidx.paging.LOG_TAG
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel

class PostPagingDataSource(
    private val postServices: PostServices
) : PagingSource<Int, PostResponseModel>() {

    override fun getRefreshKey(state: PagingState<Int, PostResponseModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostResponseModel> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = postServices.getPagingAllPost(pager = page, limit = limit).body() ?: emptyList()
            val nextKey = if (response.isEmpty()) null else response.size.plus(page).plus(1)
            val prevKey = if (page == 1) null else response.size.minus(limit)

            Log.e(
                "PostPagingDataSource",
                "page: $page, response.size: ${response.size}, nextKey: $nextKey, prevKey: $prevKey"
            )

            LoadResult.Page(
                data = response,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}