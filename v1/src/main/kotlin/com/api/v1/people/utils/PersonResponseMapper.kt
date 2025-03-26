package com.api.v1.people.utils

import com.api.v1.people.domain.Person
import com.api.v1.people.dtos.PersonResponseDto

class PersonResponseMapper {

    private var fullNameFormatter: FullNameFormatter

    constructor(fullNameFormatter: FullNameFormatter) {
        this.fullNameFormatter = fullNameFormatter
    }

    fun Person.toDto(): PersonResponseDto {
        return PersonResponseDto(
            id,
            fullNameFormatter.formatFullName(firstName, middleName, lastName)
        )
    }
}