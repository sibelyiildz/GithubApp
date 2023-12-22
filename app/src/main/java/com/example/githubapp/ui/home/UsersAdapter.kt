package com.example.githubapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.remote.model.UserItemResponse
import com.example.githubapp.databinding.ListItemUserBinding
import com.example.githubapp.extension.setImageUrl

class UsersAdapter : ListAdapter<UserItemResponse, UsersAdapter.ViewHolder>(DIFF) {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<UserItemResponse>() {
            override fun areItemsTheSame(
                oldItem: UserItemResponse,
                newItem: UserItemResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserItemResponse,
                newItem: UserItemResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    inner class ViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: UserItemResponse) {
            with(binding) {
                userImage.setImageUrl(root.context, data.avatarUrl)
                userName.text = data.login
            }
        }
    }

}