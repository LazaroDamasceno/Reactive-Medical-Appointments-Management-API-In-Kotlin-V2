package com.api.v1.common

import java.time.LocalDate

fun LocalDate.isBeforeToday(date: LocalDate): Boolean {
    return LocalDate.now().isBefore(date)
}