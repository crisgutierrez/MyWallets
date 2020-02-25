package com.example.firstbitpandachallenge.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Inflate [layoutRes]
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**
 * Round a double to the [decimals] setted, by default it will round to 2 decimals.
 */
fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()

/**
 * Filter the list using the [filterRule] setted, if the [type] is 0 (mean all the items) it will
 * return a list without filtering it.
 */
fun <T> List<T>.filterByType(type: Int, filterRule: (T) -> Boolean): List<T> =
    if (type == 0) {
        this
    } else {
        filter(filterRule)
    }

