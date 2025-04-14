package com.api.v2.people.utils

import com.api.v2.people.domain.exposed.Person

fun Person.formatFullName(): String {
    if (middleName.isNullOrBlank()) {
        return "$firstName $lastName"
    }
    return "$firstName $middleName $lastName"
}