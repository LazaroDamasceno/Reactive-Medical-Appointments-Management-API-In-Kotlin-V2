package com.api.v1.people.domain

import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository: MongoRepository<Person, String>