package com.example.githubapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.ListItemUserBinding
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.extension.getDrawable
import com.example.githubapp.extension.setImageUrl

class UsersAdapter(private val events: (Event) -> Unit) :
    ListAdapter<UserItemModel, UsersAdapter.ViewHolder>(DIFF) {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<UserItemModel>() {
            override fun areItemsTheSame(
                oldItem: UserItemModel,
                newItem: UserItemModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserItemModel,
                newItem: UserItemModel
            ): Boolean {
                return oldItem.isFavorite == newItem.isFavorite
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


    fun updateItem(user: UserItemModel, isFavorite: Boolean) {
        currentList.find { it.id == user.id }?.isFavorite = isFavorite
        notifyItemChanged(currentList.indexOf(currentList.find { it.id == user.id }))
    }

    inner class ViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: UserItemModel) {
            with(binding) {
                userImage.setImageUrl(root.context, data.avatarUrl)
                userName.text = data.login
                favoriteIcon.setImageDrawable(
                    if (data.isFavorite) getDrawable(R.drawable.ic_star_filled) else getDrawable(
                        R.drawable.ic_star
                    )
                )

                favoriteIcon.setOnClickListener {
                    events.invoke(
                        if (data.isFavorite) Event.RemoveFavorite(data) else Event.AddFavorite(
                            data
                        )
                    )
                }
            }
        }
    }

    sealed class Event {
        data class AddFavorite(val user: UserItemModel) : Event()
        data class RemoveFavorite(val user: UserItemModel) : Event()
    }
}