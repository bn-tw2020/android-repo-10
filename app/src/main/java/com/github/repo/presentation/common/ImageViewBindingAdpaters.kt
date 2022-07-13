package com.github.repo.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.repo.R

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideApp.with(imageView)
            .load(url)
            .placeholder(R.drawable.ic_user)
            .into(imageView)
    }
}