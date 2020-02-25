package com.example.firstbitpandachallenge.model

import java.text.SimpleDateFormat

class Time (
    val unix: String = ""
): Comparable<Time> {

    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy")

    /**
     * We compare the time by date
     */
    override fun compareTo(other: Time): Int {
        val currentTimeDate = dateFormatter.parse(unix)
        val otherTimeDate = dateFormatter.parse(other.unix)

        return otherTimeDate?.compareTo(currentTimeDate) ?: 0
    }
}
