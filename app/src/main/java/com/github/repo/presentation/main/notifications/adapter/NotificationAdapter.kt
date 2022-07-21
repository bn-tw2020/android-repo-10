package com.github.repo.presentation.main.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.repo.databinding.ItemNotificationBinding
import com.github.repo.domain.model.Notification
import com.github.repo.utils.DateUtils

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private val notificationDtoList = ArrayList<Notification>()

    fun addItemList(list: List<Notification>) {
        notificationDtoList.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int): Notification {
        val notification = notificationDtoList.removeAt(position)
        notifyItemRemoved(position)

        return notification
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemNotificationBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notificationDtoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = notificationDtoList.size

    inner class ViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification) {
            binding.notification = notification
            binding.tvUpdateTime.text = DateUtils.getUpdateDate(notification.updateTime)
        }
    }
}