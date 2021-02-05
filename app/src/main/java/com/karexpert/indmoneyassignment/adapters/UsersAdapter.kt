package com.karexpert.indmoneyassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karexpert.indmoneyassignment.R
import com.karexpert.indmoneyassignment.databinding.ItemRowBinding
import com.karexpert.indmoneyassignment.models.UserResponse

class UsersAdapter(private val context: Context) : PagedListAdapter<UserResponse,UsersAdapter.MyViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemRowBinding = DataBindingUtil.inflate(inflater, R.layout.item_row,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.user = getItem(position)
    }

    class MyViewHolder(val itemBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemRowBinding? = null

        init {
            this.binding = itemBinding
        }

    }
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserResponse>() {
            override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean =
                newItem == oldItem
        }
    }

}