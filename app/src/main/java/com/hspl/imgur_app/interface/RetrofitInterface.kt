package com.hspl.imgur_app.`interface`

import com.hspl.imgur_app.model.APIResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("3/gallery/top/time/week/false/0.json")
    fun getImagesData(): Call<APIResponseModel>
}