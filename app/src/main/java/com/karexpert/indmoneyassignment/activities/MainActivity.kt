package com.karexpert.indmoneyassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.karexpert.indmoneyassignment.R
import com.karexpert.indmoneyassignment.adapters.UsersAdapter
import com.karexpert.indmoneyassignment.databinding.ActivityMainBinding
import com.karexpert.indmoneyassignment.models.UserResponse
import com.karexpert.indmoneyassignment.viewmodelfactories.UserViewModelFactory
import com.karexpert.indmoneyassignment.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        initRecyclerView()

        val userViewModel = ViewModelProvider(this, UserViewModelFactory(this)).get(UserViewModel::class.java)
        userViewModel.getData().observe(this, object : Observer<PagedList<UserResponse>> {
            override fun onChanged(t: PagedList<UserResponse>?) {
                adapter.submitList(t)
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerMain.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = UsersAdapter(this)
        binding.recyclerMain.adapter = adapter

    }
}
