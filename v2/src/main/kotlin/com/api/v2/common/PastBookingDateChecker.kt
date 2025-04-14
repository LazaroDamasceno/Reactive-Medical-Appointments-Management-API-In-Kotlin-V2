package com.api.v2.common

import java.time.LocalDate

class PastBookingDateChecker {
    companion object {
        fun isBeforeToday(date: LocalDate): Boolean {
            return LocalDate.now().isAfter(date)
        }
    }
}
