package com.api.v1.people.utils

import com.api.v1.people.domain.Person

class FullNameFormatter {

    fun formatFullName(firstName: String,
                           middleName: String?,
                           lastName: String
    ): String {
        if (middleName.isNullOrBlank()) {
            return "$firstName $lastName"
        }
        return "$firstName $middleName $lastName"
    }

    fun Person.formatFullName(): String {
        if (middleName.isNullOrBlank()) {
            return "$firstName $lastName"
        }
        return "$firstName $middleName $lastName"
    }
}