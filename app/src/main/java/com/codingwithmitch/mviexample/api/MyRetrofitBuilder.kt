package com.codingwithmitch.mviexample.api

import com.codingwithmitch.mviexample.util.LiveDataCallAdapter
import com.codingwithmitch.mviexample.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {

    private const val BASE_URL = "https://open-api.xyz/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder.build()
            .create(ApiService::class.java)
    }

}