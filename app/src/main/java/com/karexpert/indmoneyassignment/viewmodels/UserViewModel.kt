package com.karexpert.indmoneyassignment.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.karexpert.indmoneyassignment.datasourcefactories.UserDataSourceFactory
import com.karexpert.indmoneyassignment.datasources.UserDataSource
import com.karexpert.indmoneyassignment.models.UserResponse

class UserViewModel(private val context: Context) : ViewModel() {

    private var listUsers : LiveData<PagedList<UserResponse>> = MutableLiveData<PagedList<UserResponse>>()
    private var mutableLiveData = MutableLiveData<UserDataSource>()

    init {
        val factory : UserDataSourceFactory by lazy {
            UserDataSourceFactory(context)
        }
        mutableLiveData = factory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        listUsers = LivePagedListBuilder(factory, config)
            .build()

    }

    fun getData() : LiveData<PagedList<UserResponse>>{
        return listUsers
    }


}