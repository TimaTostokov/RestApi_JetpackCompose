package com.bilim_asker.restapi_jetpackcompose.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilim_asker.restapi_jetpackcompose.data.api.model.PostResponseModel
import com.bilim_asker.restapi_jetpackcompose.presentation.theme.RestApi_JetpackComposeTheme
import com.bilim_asker.restapi_jetpackcompose.utils.NetworkResult

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val state = mainViewModel.allPostResponse.observeAsState().value ?: NetworkResult.Loading()

    when (state) {
        is NetworkResult.Success -> {
            SuccessScreen(state.data ?: listOf(), mainViewModel)
        }

        is NetworkResult.Error -> {
            ErrorScreen(state.message ?: "Something went wrong")
        }

        is NetworkResult.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun SuccessScreen(postResponses: List<PostResponseModel>, mainViewModel: MainViewModel) {
    Column {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Button(
                onClick = { mainViewModel.postPost() }
            ) {
                Text(text = "POST")
            }
            Button(
                onClick = { mainViewModel.putPost() }
            ) {
                Text(text = "PUT")
            }
            Button(
                onClick = { mainViewModel.patchPost() }
            ) {
                Text(text = "PATCH")
            }
            Button(
                onClick = { mainViewModel.deletePost() }
            ) {
                Text(text = "DLETE")
            }

        }
        LazyColumn {
            items(postResponses) { item ->
                PostItem(item)
            }
        }
    }
}


@Composable
fun PostItem(item: PostResponseModel) {
    val title = item.title ?: "null"
    val body = item.body ?: "null"

    Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
        Text(text = item.id.toString(), fontSize = 24.sp)
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = body, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Red)
    }

}

@Composable
fun LoadingScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RestApi_JetpackComposeTheme {
    }
}