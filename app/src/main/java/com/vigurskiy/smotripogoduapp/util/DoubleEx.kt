@file:JvmName("DoubleEx")

package com.vigurskiy.smotripogoduapp.util

import java.text.DecimalFormat

private val tempFormat = DecimalFormat("+0;-#")
private const val KELVIN_OFFSET = 273.15
private const val CELSIUS_SIGN = " \u2103"

fun Double?.toCelcius(): String =
    this?.minus(KELVIN_OFFSET)?.let {
        tempFormat.format(it).plus(CELSIUS_SIGN)
    } ?: "N/A $CELSIUS_SIGN"
