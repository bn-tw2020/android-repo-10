package com.github.repo.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.repo.databinding.ItemRepositoryBinding
import com.github.repo.domain.model.GithubRepository

class RepositoryAdapter :
    ListAdapter<GithubRepository, RepositoryAdapter.RepositoryViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: GithubRepository) {
            binding.repository = repository
        }

    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<GithubRepository>() {
            override fun areItemsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
                return oldItem == newItem
            }
        }
    }

}