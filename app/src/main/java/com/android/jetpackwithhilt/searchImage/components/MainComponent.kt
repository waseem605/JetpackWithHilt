package com.android.jetpackwithhilt.searchImage.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.android.jetpackwithhilt.searchImage.network.model.Hit

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MainComponent(viewModel: MainViewModel = hiltViewModel()) {
    val query: MutableState<String> = remember {
        mutableStateOf("")
    }
    val result = viewModel.list.value
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = query.value, onValueChange = {
                        query.value = it
                    },
                    enabled = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    } ,
                    label = {
                        Text("Search")
                    }
                )
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { viewModel.getImageList(query.value) }
                ) {
                    Text(text = "Search", maxLines = 1)
                }

            }



            if (result.isLoading) {
                Log.d("TAG", "MainContent: in the loading")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            if (result.data.isNotEmpty()) {
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    Log.d("TAG", "MainContent: Your Token")
                    viewModel.list.value.data.let { hit ->
//                        items()
                        items(hit) {
                            MainContentItem(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentItem(hit: Hit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = hit.largeImageURL),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}