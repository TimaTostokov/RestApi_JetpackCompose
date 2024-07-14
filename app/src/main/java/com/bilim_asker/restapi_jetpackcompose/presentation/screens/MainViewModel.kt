package com.bilim_asker.restapi_jetpackcompose.presentation.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel
import com.bilim_asker.restapi_jetpackcompose.domain.usecases.DeletePostUseCase
import com.bilim_asker.restapi_jetpackcompose.domain.usecases.GetAllPostUseCase
import com.bilim_asker.restapi_jetpackcompose.domain.usecases.PatchPostUseCase
import com.bilim_asker.restapi_jetpackcompose.domain.usecases.PutPostUseCase
import com.bilim_asker.restapi_jetpackcompose.domain.usecases.PostPostUseCase
import com.bilim_asker.restapi_jetpackcompose.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deletePostUseCase: DeletePostUseCase,
    private val getAllPostUseCase: GetAllPostUseCase,
    private val posPostUseCase: PostPostUseCase,
    private val patchPostUseCase: PatchPostUseCase,
    private val putPostUseCase: PutPostUseCase
) : ViewModel() {
    private val _allPostResponse = MutableLiveData<NetworkResult<List<PostResponseModel>>>()
    val allPostResponse: LiveData<NetworkResult<List<PostResponseModel>>>
        get() = _allPostResponse

    init {
        getAllPosts()
    }

    fun getPagingAllPost() = getAllPostUseCase.invokePaging()

    private fun getAllPosts() {
        viewModelScope.launch {
            getAllPostUseCase.invoke().let {
                _allPostResponse.value = it
            }
        }
    }

    fun postPost() {
        viewModelScope.launch {
            posPostUseCase.invoke(
                body = PostResponseModel(
                    title = "Jolchubekov",
                    body = "Temirlan"
                )
            )
        }
    }

    fun putPost() {
        viewModelScope.launch {
            putPostUseCase.invoke(
                id = "3",
                body = PostResponseModel(title = "Jolchubekov", body = "Temirlan")
            ).let {
                Log.d("akaza", "data: ${it.data}")
            }
        }
    }

    fun patchPost() {
        viewModelScope.launch {
            patchPostUseCase.invoke(
                id = "3",
                body = PostResponseModel(title = "Jolchubekov", body = "Temirlan")
            ).let {
                Log.d("akaza", "data: ${it.data}")
            }
        }
    }

    fun deletePost() {
        viewModelScope.launch {
            deletePostUseCase.invoke(id = "3").let {
                Log.d("akaza", "data: ${it.data}")
            }
        }
    }

}