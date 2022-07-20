package com.github.repo.presentation.main

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.repo.R
import com.github.repo.databinding.ActivityMainBinding
import com.github.repo.presentation.common.Clickable
import com.github.repo.presentation.common.onSuccess
import com.github.repo.presentation.main.issue.IssueFragment
import com.github.repo.presentation.main.notifications.NotificationsFragment
import com.github.repo.presentation.profile.ProfileFragment
import com.github.repo.presentation.profile.ProfileViewModel
import com.github.repo.presentation.search.SearchFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), Clickable {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
        observeData()
        onSaveFragment()
    }

    private fun initView() {
        appBarSetting()
        toggleButtonSetting()
    }

    private fun observeData() {
        viewModel.uiState.observe(this) { state ->
            with(state) {
                onSuccess { handleSuccess(it.profileImgUrl) }
            }
        }
    }

    private fun handleSuccess(imageUrl: String) {
        binding.profileImgUrl = imageUrl
    }

    private fun toggleButtonSetting() {
        binding.rgFragmentTab.setOnCheckedChangeListener { a, id ->
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
            if (currentFragment?.tag == getString(R.string.fragment_search)
                || currentFragment?.tag == getString(R.string.fragment_profile)
            ) return@setOnCheckedChangeListener
            when (id) {
                R.id.btn_issue -> changeFragment(
                    IssueFragment(),
                    getString(R.string.fragment_issue),
                    false
                )
                R.id.btn_notifications -> changeFragment(
                    NotificationsFragment(),
                    getString(R.string.fragment_notifications),
                    false
                )
            }
        }
    }

    private fun appBarSetting() {
        with(binding) {
            btnSearch.setOnClickListener {
                changeFragment(
                    SearchFragment(),
                    getString(R.string.fragment_search)
                )
            }
            btnProfile.setOnClickListener {
                changeFragment(
                    ProfileFragment(),
                    getString(R.string.fragment_profile)
                )
            }
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String, addBackStack: Boolean = true) {
        when (tag) {
            getString(R.string.fragment_search) -> hideAppBar()
            getString(R.string.fragment_profile) -> hideAppBar()
        }

        if (addBackStack) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, fragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)

        when (currentFragment?.tag) {
            getString(R.string.fragment_profile) -> {
                supportFragmentManager.popBackStack()
                showAppBar()
            }
            getString(R.string.fragment_search) -> {
                supportFragmentManager.popBackStack()
                showAppBar()
            }
            else -> super.onBackPressed()
        }
    }

    private fun onSaveFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        when (currentFragment?.tag) {
            getString(R.string.fragment_profile) -> hideAppBar()
            getString(R.string.fragment_search) -> hideAppBar()
        }
    }

    private fun showAppBar() {
        binding.toolbar.isVisible = true
        binding.rgFragmentTab.isVisible = true
    }

    private fun hideAppBar() {
        binding.toolbar.isGone = true
        binding.rgFragmentTab.isGone = true
    }

    override fun onClickBackButton() {
        supportFragmentManager.popBackStack()
        showAppBar()
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val focusView = currentFocus ?: return super.dispatchTouchEvent(event)
        event ?: return super.dispatchTouchEvent(event)
        val rect = Rect()
        focusView.getGlobalVisibleRect(rect)
        val x = event.x.toInt()
        val y = event.y.toInt()
        if (!rect.contains(x, y)) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focusView.windowToken, 0)
        }
        return super.dispatchTouchEvent(event)

    }
}