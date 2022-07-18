package com.github.repo.presentation.main.issue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import com.github.repo.R
import com.github.repo.databinding.ItemSpinnerContentBinding
import com.github.repo.databinding.ItemSpinnerTitleBinding

class SpinnerAdapter(
    private val context: Context,
    private val items: List<String>
) : BaseAdapter() {

    private var selectedPosition: Int = 0

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
        onChecked(position, binding.tvSpinnerContent)
        return binding.root
    }

    private fun onChecked(position: Int, view: CheckedTextView) {
        when (position) {
            selectedPosition -> {
                view.isChecked = true
                view.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            else -> {
                view.isChecked = false
                view.setTextColor(ContextCompat.getColor(context, R.color.grey))
            }
        }
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
    }
}