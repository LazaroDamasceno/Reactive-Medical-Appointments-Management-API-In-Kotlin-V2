package com.api.v1.cards.domain

import com.api.v1.cards.domain.exposed.Card
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CardRepository: CoroutineCrudRepository<Card, String>