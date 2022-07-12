package com.github.repo.data.datasource

import android.content.Context
import androidx.core.content.edit

class TokenSharedPreference(context: Context) {

    private val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreference.edit {
            remove(KEY)
            putString(KEY, token)
        }
    }

    fun getToken(): String = sharedPreference.getString(KEY, "") ?: ""

    companion object {
        const val KEY = "token"
    }
}