package com.github.repo.presentation.main.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.repo.databinding.ItemNotificationBinding
import com.github.repo.domain.dto.NotificationDto

class NotificationRvAdapter ()
    : RecyclerView.Adapter<NotificationRvAdapter.ViewHolder>(){

    private val notificationDtoList = ArrayList<NotificationDto>()

    var onItemClick:() -> Unit = {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notificationDtoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = notificationDtoList.size

    inner class ViewHolder(val binding: ItemNotificationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notificationDto: NotificationDto){
            binding.ivThumbnail

            binding.tvRepoName
            binding.tvNotificationTitle
            binding.tvIssueNumber

            binding.tvUpdateTime
            binding.tvCommentCount
        }
    }
}