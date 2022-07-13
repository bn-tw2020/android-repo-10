package com.github.repo.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.repo.R
import com.github.repo.databinding.ActivityMainBinding
import com.github.repo.presentation.common.Clickable
import com.github.repo.presentation.main.issue.IssueFragment
import com.github.repo.presentation.main.notifications.NotificationsFragment
import com.github.repo.presentation.profile.ProfileFragment
import com.github.repo.presentation.search.SearchFragment

class MainActivity : AppCompatActivity(), Clickable {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {
        appBarSetting()
        toggleButtonSetting()
        changeFragment(IssueFragment(), getString(R.string.fragment_issue), false)
    }

    private fun toggleButtonSetting() {
        binding.rgFragmentTab.setOnCheckedChangeListener { _, id ->
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
}