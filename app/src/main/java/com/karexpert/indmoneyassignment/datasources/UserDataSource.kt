package com.karexpert.indmoneyassignment.datasources

import android.content.Context
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.karexpert.indmoneyassignment.Utility.Utility
import com.karexpert.indmoneyassignment.Utility.Utility.showProgressBar
import com.karexpert.indmoneyassignment.api.ApiClient
import com.karexpert.indmoneyassignment.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource(private val context: Context) : PageKeyedDataSource<Int, UserResponse>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserResponse>) {
            getUsers(callback)

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserResponse>) {
            val nextPageNo = params.key + 1
            getMoreUsers(params.key, nextPageNo, callback)

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserResponse>) {
            val previousPageNo = if (params.key > 1) params.key - 1 else 0
            getMoreUsers(params.key, previousPageNo, callback)

    }

    private fun getUsers(callback: LoadInitialCallback<Int, UserResponse>) {
        context.showProgressBar()


        ApiClient.apiService.getUsers(1,10).enqueue(object : Callback<List<UserResponse>> {
            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Utility.hideProgressBar()

            }

            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                Utility.hideProgressBar()

                val userResponse = response.body()
                userResponse?.let { callback.onResult(it, null, 2) }
            }

        })

    }

    private fun getMoreUsers(pageNo: Int, previousOrNextPageNo: Int, callback: LoadCallback<Int, UserResponse>) {

        ApiClient.apiService.getUsers(pageNo,10).enqueue(object : Callback<List<UserResponse>> {
            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Utility.hideProgressBar()

            }

            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                val userResponse = response.body()
                userResponse?.let { callback.onResult(it, previousOrNextPageNo) }
            }

        })

    }

}