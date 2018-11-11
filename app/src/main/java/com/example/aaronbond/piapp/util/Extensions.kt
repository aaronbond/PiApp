package com.example.aaronbond.piapp.util

import java.math.BigDecimal

inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum = 0.0.toBigDecimal()

    for (element in this) {
        sum += selector(element)
    }

    return sum
}

fun Int.isEven(): Boolean {
    return this % 2 == 0
}

fun Long.isEven(): Boolean {
    return this % 2 == 0L
}

