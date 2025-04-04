package com.api.v1.cards

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CardRepository: CoroutineCrudRepository<Card, String>