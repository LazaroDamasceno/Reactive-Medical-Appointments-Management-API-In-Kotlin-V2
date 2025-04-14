package com.api.v2.cards.domain

import com.api.v2.cards.domain.exposed.Card
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CardRepository: CoroutineCrudRepository<Card, String>