package com.github.repo.presentation.common

import android.widget.ImageView
import androidx.core.content.ContextCompat
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

@BindingAdapter("setState")
fun setStateImage(imageView: ImageView, state: String) {
    when (state) {
        "open" -> imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_issue_open
            )
        )
        "closed" -> imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_issue_closed
            )
        )
    }
}