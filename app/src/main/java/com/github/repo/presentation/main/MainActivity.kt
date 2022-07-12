package com.github.repo.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.repo.R
import com.github.repo.databinding.ActivityMainBinding
import com.github.repo.presentation.profile.ProfileFragment
import com.github.repo.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {
        appBarSetting()
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

    private fun changeFragment(fragment: Fragment, tag: String) {
        when(tag){
            getString(R.string.fragment_search) -> {binding.toolbar.isGone = true}
            getString(R.string.fragment_profile) -> {binding.toolbar.isGone = true}
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment, tag)
            .addToBackStack(tag)
            .commitAllowingStateLoss()
    }
}