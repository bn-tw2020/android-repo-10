package com.github.repo.presentation.common.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.github.repo.R
import com.github.repo.databinding.ViewProfileGithubDataItemBinding
import com.github.repo.presentation.common.Constant.BLANK

class CustomProfileGithubDataItem(
    context: Context,
    attrs: AttributeSet?
) : Toolbar(context, attrs) {

    private val binding: ViewProfileGithubDataItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_profile_github_data_item,
            this,
            true
        )

    private var title = BLANK
    private var icon = R.drawable.ic_check
    var count = 0
        set(value) {
            field = value
            binding.tvCount.text = value.toString()
        }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomProfileGithubDataItem)
        title = a.getString(R.styleable.CustomProfileGithubDataItem_name).toString()
        icon = a.getResourceId(
            R.styleable.CustomProfileGithubDataItem_icon,
            R.drawable.ic_check
        )
        count = a.getInt(R.styleable.CustomProfileGithubDataItem_count, 0)
        with(binding) {
            tvTitle.text = title
            ivIcon.setImageResource(icon)
        }
        a.recycle()
    }
}