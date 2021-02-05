package com.karexpert.indmoneyassignment.datasourcefactories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.karexpert.indmoneyassignment.datasources.UserDataSource
import com.karexpert.indmoneyassignment.models.UserResponse

class UserDataSourceFactory(private val context: Context) : DataSource.Factory<Int, UserResponse>() {

    val mutableLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, UserResponse> {
        val userDataSource = UserDataSource(context)
        mutableLiveData.postValue(userDataSource)
        return userDataSource
    }

}