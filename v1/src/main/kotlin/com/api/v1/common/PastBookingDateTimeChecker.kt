package com.api.v1.common

import java.time.LocalDate

class PastBookingDateTimeChecker {
    companion object {
        fun isBeforeToday(date: LocalDate): Boolean {
            return LocalDate.now().isAfter(date)
        }
    }
}
