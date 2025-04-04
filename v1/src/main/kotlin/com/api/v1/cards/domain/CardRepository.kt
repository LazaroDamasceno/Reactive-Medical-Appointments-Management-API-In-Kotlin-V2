package com.api.v1.cards.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CardRepository: CoroutineCrudRepository<Card, String>