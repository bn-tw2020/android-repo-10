package com.github.repo.presentation.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import kotlin.math.roundToInt

@BindingAdapter("formatNumber")
fun formatNumber(textView: TextView, star: Int) {
    textView.text = when {
        star >= 1E9 -> "${(star.toFloat() / 1E9).toInt()}.${(star % 1E9 / 1E8).roundToInt()}B"
        star >= 1E6 -> "${(star.toFloat() / 1E6).toInt()}.${(star % 1E6 / 1E5).roundToInt()}M"
        star >= 1E3 -> "${(star.toFloat() / 1E3).toInt()}.${(star % 1E3 / 1E2).roundToInt()}K"
        else -> NumberFormat.getInstance().format(star)
    }
}