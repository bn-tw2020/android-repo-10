package com.github.repo.presentation.main.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.repo.databinding.ItemNotificationBinding
import com.github.repo.domain.dto.NotificationDto
import java.text.SimpleDateFormat
import java.util.*

class NotificationRvAdapter() : RecyclerView.Adapter<NotificationRvAdapter.ViewHolder>() {

    private val notificationDtoList = ArrayList<NotificationDto>()

    var onItemClick: () -> Unit = {}
    fun addItemList(list: List<NotificationDto>) {
        notificationDtoList.addAll(list)
        notifyDataSetChanged()
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

    private val mFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
    private val currentDate: Date by lazy { Date(System.currentTimeMillis()) }

    private fun getUpdateDate(date: String): String {
        val updateDate: Date = mFormat.parse(date)
        updateDate.compareTo(currentDate)

        val diff: Long = currentDate.getTime() - updateDate.getTime()
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val years = days / 365

        return if (years > 0) "${years}년 전"
        else if (days > 0) "${days}일 전"
        else if (hours > 0) "${hours}시간 전"
        else if (minutes > 0) "${minutes}분 전"
        else "${seconds}초 전"
    }

    inner class ViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notificationDto: NotificationDto) {
            Glide.with(binding.root)
                .load(notificationDto.thumbnailUrl)
                .into(binding.ivThumbnail)

            binding.tvRepoName.text = notificationDto.repoName
            binding.tvNotificationTitle.text = notificationDto.notificationTitle
            binding.tvIssueNumber.text = "#${notificationDto.issueNumber}"

            binding.tvUpdateTime.text = getUpdateDate(notificationDto.updateTime)
            binding.tvCommentCount.text = notificationDto.commentCount.toString()
        }
    }
}