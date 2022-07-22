package com.github.repo.presentation.main.issue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import com.github.repo.R
import com.github.repo.databinding.ItemSpinnerContentBinding
import com.github.repo.databinding.ItemSpinnerTitleBinding

class SpinnerAdapter(
    private val items: List<String>,
    private val onChecked: (CheckedTextView, Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
            ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_spinner_title, parent, false)
        val binding = ItemSpinnerTitleBinding.bind(view)
        binding.title = items[position]
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =
            ItemSpinnerContentBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        binding.content = items[position]
        onChecked(binding.tvSpinnerContent, position)
        return binding.root
    }

}