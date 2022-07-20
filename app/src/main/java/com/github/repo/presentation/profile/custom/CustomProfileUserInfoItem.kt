package com.github.repo.presentation.profile.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.res.getStringOrThrow
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.github.repo.R
import com.github.repo.databinding.ViewProfileUserInfoItemBinding

class CustomProfileUserInfoItem(
    context: Context,
    attrs: AttributeSet?
) : Toolbar(context, attrs) {

    private val binding: ViewProfileUserInfoItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_profile_user_info_item,
            this,
            true
        )

    var contentText: String = ""
        set(value) {
            field = value
            binding.tvContent.text = value
        }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomProfileUserInfoItem)
        with(binding) {
            contentText = a.getString(R.styleable.CustomProfileUserInfoItem_contentText) ?: ""
            ivIcon.setImageResource(
                a.getResourceId(
                    R.styleable.CustomProfileUserInfoItem_contentIcon,
                    R.drawable.ic_check
                )
            )
            binding.tvContent.autoLinkMask =
                a.getInt(R.styleable.CustomProfileUserInfoItem_itemType, 0x00)
        }
        a.recycle()
    }
}