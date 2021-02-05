package com.karexpert.indmoneyassignment.api

import com.karexpert.indmoneyassignment.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/orgs/octokit/repos")
    fun getUsers(@Query ("page") page:Int,@Query ("per_page") per_page:Int): Call<List<UserResponse>>

}