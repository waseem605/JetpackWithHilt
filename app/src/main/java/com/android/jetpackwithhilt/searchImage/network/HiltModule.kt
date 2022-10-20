package com.android.jetpackwithhilt.searchImage.network

import com.android.jetpackwithhilt.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {


    @Singleton
    @Provides
    fun provideApiService(): ApiServicePixa {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiServicePixa::class.java)
    }

    @Provides
    fun provideMainRepository(apiService: ApiServicePixa): MainRepository {
        return MainRepository(apiService = apiService)
    }
}