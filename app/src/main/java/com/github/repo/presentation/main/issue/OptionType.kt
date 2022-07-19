package com.github.repo.presentation.main.issue

enum class OptionType(val position: Int, val optionName: String) {
    OPEN(0, "open"),
    CLOSED(1, "closed"),
    ALL(2, "all");


    companion object {
        fun create(): List<String> {
            return values().map {
                it.optionName.replaceFirstChar(Char::titlecase)
            }
        }
    }
}