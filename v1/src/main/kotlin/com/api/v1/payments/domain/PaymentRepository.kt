package com.api.v1.payments.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PaymentRepository: CoroutineCrudRepository<Payment, String>