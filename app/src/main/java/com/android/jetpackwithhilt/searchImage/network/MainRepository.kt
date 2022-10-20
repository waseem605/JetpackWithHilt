package com.android.jetpackwithhilt.searchImage.network

import com.android.jetpackwithhilt.searchImage.network.model.PixabyResponseModel
import com.android.jetpackwithhilt.utils.Constant
import com.android.jetpackwithhilt.utils.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiServicePixa) {
    private val imageType = "photo"
    private val order = "popular"
    private val perPage = "100"

    suspend fun getQueryItems(q:String): Resource<PixabyResponseModel> {
        return  try{

            val result = apiService.getWallpaperMain(Constant.KEY,q,imageType,order,perPage)
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }


}