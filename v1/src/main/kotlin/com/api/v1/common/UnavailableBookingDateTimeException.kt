package com.api.v1.common

import java.time.LocalDateTime

class UnavailableBookingDateTimeException(dateTime: LocalDateTime)
    : RuntimeException("The booking datetime $dateTime is already associated with an active medical slot.")