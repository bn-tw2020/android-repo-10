package com.github.repo.presentation.main.issue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.repo.databinding.ItemIssueBinding
import com.github.repo.domain.model.GithubIssue
import com.github.repo.utils.DateUtils

class IssueAdapter :
    ListAdapter<GithubIssue, IssueAdapter.IssueViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding =
            ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IssueViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(issue: GithubIssue) {
            binding.issue = issue
            binding.tvUpdateTime.text = DateUtils.calculateTime(issue.createdAt)
        }

    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<GithubIssue>() {
            override fun areItemsTheSame(oldItem: GithubIssue, newItem: GithubIssue): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: GithubIssue, newItem: GithubIssue): Boolean {
                return oldItem == newItem
            }
        }
    }

}