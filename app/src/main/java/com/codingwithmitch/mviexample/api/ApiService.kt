package com.codingwithmitch.mviexample.api

import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/placeholder/user/{userId}")
    fun getUser(@Path("userId") userId: String): LiveData<GenericApiResponse<User>>

    @GET("/placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>
}
