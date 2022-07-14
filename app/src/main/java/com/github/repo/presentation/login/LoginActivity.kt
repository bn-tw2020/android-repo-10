package com.github.repo.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.github.repo.R
import com.github.repo.config.GITHUB_OAUTH_URL
import com.github.repo.databinding.ActivityLoginBinding
import com.github.repo.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
//        initView()
//        observeData()
    }

    private fun initView() {
        githubLogin()
    }

    private fun observeData() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UiState.Error -> handleError()
                is UiState.Loading -> handleLoading()
                is UiState.GetToken -> handleSuccess()
            }
        }
    }

    private fun handleSuccess() {
        binding.pbLoading.isVisible = false
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }

    private fun handleLoading() {
        binding.pbLoading.isVisible = true
        binding.btnLogin.isEnabled = false
    }

    private fun handleError() {
        binding.pbLoading.isVisible = false
        binding.btnLogin.isEnabled = false
        Toast.makeText(this, getString(R.string.login_error_message), Toast.LENGTH_SHORT).show()
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
        code?.let { viewModel.getAccessToken(code) }
    }
}