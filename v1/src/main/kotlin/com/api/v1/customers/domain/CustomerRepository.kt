package com.api.v1.customers.domain

import com.api.v1.customers.domain.exposed.Customer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository: CoroutineCrudRepository<Customer, String>