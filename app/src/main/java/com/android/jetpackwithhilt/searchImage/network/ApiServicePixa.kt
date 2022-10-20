package com.android.jetpackwithhilt.searchImage.network

import com.android.jetpackwithhilt.searchImage.network.model.PixabyResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicePixa {

    @GET("/api/")
    suspend fun getWallpaperMain(@Query("key") key: String?,
                         @Query("q") q: String?,
                         @Query("image_type") image_type: String?,
                         @Query("order") order: String?,
                         @Query("per_page") per_page: String?)
            : PixabyResponseModel
}