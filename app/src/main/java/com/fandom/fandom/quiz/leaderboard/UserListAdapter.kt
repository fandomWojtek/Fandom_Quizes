package com.fandom.fandom.quiz.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.ItemUserBinding
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.utils.avatars

internal class UserListAdapter : ListAdapter<UserEntity, UsersListViewHolder>(object : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder =
        UsersListViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class UsersListViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val avatars = binding.root.context.avatars()

    fun bind(user: UserEntity) {
        binding.userName.text = user.userName
        binding.userPoints.text = user.points.toString()
        if (user.userPhoto.isEmpty()) {
            binding.avatar.setImageDrawable(avatars[user.avatar])
        } else {
            user.userPhoto.isNotEmpty().let {
                Glide.with(binding.avatar.context)
                    .load(it)
                    .circleCrop()
                    .into(binding.avatar)
            }
        }
    }

    private fun getDrawable(name: Int) = ContextCompat.getDrawable(binding.avatar.context, name)
}