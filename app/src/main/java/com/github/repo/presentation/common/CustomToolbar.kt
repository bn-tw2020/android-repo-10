package com.github.repo.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.github.repo.R
import com.github.repo.databinding.ViewToolbarBinding

class CustomToolbar(
    context: Context,
    attrs: AttributeSet?
) : Toolbar(context, attrs) {

    private val binding: ViewToolbarBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_toolbar, this, true)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar)
        with(binding) {
            tvTitle.text = a.getString(R.styleable.CustomToolbar_toolbarTitle).toString()
            tvTitle.setTextColor(a.getColor(R.styleable.CustomToolbar_toolbarTitleColor, 0))
            tbMain.navigationIcon = a.getDrawable(R.styleable.CustomToolbar_navigationIcon)
        }
        a.recycle()
    }

    fun onClickNavigationIcon(callback: () -> Unit) {
        binding.tbMain.setNavigationOnClickListener { callback() }
    }
}