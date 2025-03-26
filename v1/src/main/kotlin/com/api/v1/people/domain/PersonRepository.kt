package com.api.v1.people.domain

import com.api.v1.people.domain.exposed.Person
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PersonRepository: CoroutineCrudRepository<Person, String>