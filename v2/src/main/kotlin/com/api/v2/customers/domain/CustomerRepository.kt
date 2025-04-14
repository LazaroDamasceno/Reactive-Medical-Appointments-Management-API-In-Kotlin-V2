package com.api.v2.customers.domain

import com.api.v2.customers.domain.exposed.Customer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository: CoroutineCrudRepository<Customer, String>