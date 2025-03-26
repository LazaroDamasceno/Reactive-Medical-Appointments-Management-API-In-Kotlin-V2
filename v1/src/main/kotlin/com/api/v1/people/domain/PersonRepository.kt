package com.api.v1.people.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PersonRepository: CoroutineCrudRepository<Person, String>