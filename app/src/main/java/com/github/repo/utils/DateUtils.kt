package com.github.repo.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

object DateUtils {
    fun calculateTime(stringDate: String): String? {
        val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").apply {
            timeZone = TimeZone.getTimeZone("Aisa/Seoul")
        }
        val date = format.parse(stringDate) ?: return ""
        val currentTime = System.currentTimeMillis()
        val registerTime = date.time
        var differenceTime = (currentTime - registerTime) / 1000

        if (differenceTime < TimeValue.SEC.value) {
            return "${differenceTime}${TimeValue.SECOND_AGE}"
        } else {
            for (timeValue in TimeValue.values()) {
                differenceTime /= timeValue.value
                if (differenceTime < timeValue.maximum) {
                    return "${differenceTime}${timeValue.message}"
                }
            }
        }
        return null
    }
}

enum class TimeValue(val value: Int, val maximum: Int, val message: String) {
    SEC(60, 60, TimeValue.MIN_AGO),
    MIN(60, 24, TimeValue.TIME_AGE),
    HOUR(24, 30, TimeValue.DAY_AGE),
    DAY(30, 12, TimeValue.MONTH_AGE),
    MONTH(12, Int.MAX_VALUE, TimeValue.YEAR_AGE);

    companion object {
        const val SECOND_AGE = "초 전"
        const val MIN_AGO = "분 전"
        const val TIME_AGE = "시간 전"
        const val DAY_AGE = "일 전"
        const val MONTH_AGE = "달 전"
        const val YEAR_AGE = "년 전"
    }
}