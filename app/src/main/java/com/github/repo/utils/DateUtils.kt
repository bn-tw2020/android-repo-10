package com.github.repo.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

object DateUtils {
    private val mFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        .apply { timeZone = TimeZone.getTimeZone("UTC") }
    private val currentDate: Date by lazy { Date(System.currentTimeMillis()) }

    fun getUpdateDate(date: String): String {
        val updateDate: Date = mFormat.parse(date)
        updateDate.compareTo(currentDate)

        val diff: Long = abs(currentDate.time - updateDate.time)
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val years = days / 365

        return if (years > 0) "${years}년 전"
        else if (days > 0) "${days}일 전"
        else if (hours > 0) "${hours}시간 전"
        else if (minutes > 0) "${minutes}분 전"
        else "${seconds}초 전"
    }
}