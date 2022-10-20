package com.android.jetpackwithhilt.searchImage.network.model

data class PixabyResponseModel(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)