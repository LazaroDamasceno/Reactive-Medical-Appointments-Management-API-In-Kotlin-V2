package com.api.v2.common

import java.time.LocalDateTime

class UnavailableBookingDateTimeException(dateTime: LocalDateTime)
    : RuntimeException("The booking datetime $dateTime is already associated with an active medical slot.")