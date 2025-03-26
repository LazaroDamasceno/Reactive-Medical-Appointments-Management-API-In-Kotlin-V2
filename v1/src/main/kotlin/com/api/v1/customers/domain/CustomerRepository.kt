package com.api.v1.customers.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository: CoroutineCrudRepository<Customer, String>