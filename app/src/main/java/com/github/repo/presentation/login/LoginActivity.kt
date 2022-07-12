package com.github.repo.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import com.github.repo.R
import com.github.repo.config.GITHUB_OAUTH_URL
import com.github.repo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initView()
    }

    private fun initView() {
        githubLogin()
    }

    private fun githubLogin() {
        binding.btnLogin.setOnClickListener {
            val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setShareState(CustomTabsIntent.SHARE_STATE_ON)
                .setUrlBarHidingEnabled(true)
                .build()
            customTabsIntent.launchUrl(this@LoginActivity, Uri.parse(GITHUB_OAUTH_URL))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code")
        code?.let { Log.d("test", "onNewIntent: $it") }
    }
}