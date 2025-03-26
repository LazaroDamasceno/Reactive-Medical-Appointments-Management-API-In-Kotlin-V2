package com.api.v1.customers

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository: CoroutineCrudRepository<Customer, String>