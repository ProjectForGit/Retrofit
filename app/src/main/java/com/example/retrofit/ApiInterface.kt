package com.example.retrofit

import com.example.retrofit.Models.UrlImageModel
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("meow")
    fun getFile(): Call<UrlImageModel>
}