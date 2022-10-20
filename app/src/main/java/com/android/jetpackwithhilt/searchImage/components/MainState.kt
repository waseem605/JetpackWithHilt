package com.android.jetpackwithhilt.searchImage.components

import com.android.jetpackwithhilt.searchImage.network.model.Hit

data class MainState(
    val isLoading:Boolean=false,
    val data:List<Hit> = emptyList(),
    val error:String=""
)