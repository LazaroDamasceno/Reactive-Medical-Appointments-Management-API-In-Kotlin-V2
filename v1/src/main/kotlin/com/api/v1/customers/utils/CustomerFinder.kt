package com.api.v1.customers.utils

import com.api.v1.customers.domain.Customer
import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.exceptions.NonExistentCustomerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class CustomerFinder {

    private final val customerRepository: CustomerRepository

    constructor(customerRepository: CustomerRepository) {
        this.customerRepository = customerRepository
    }

    suspend fun findById(id: String): Customer {
        return withContext(Dispatchers.IO) {
            val customer = customerRepository
                .findById(id)
            if (customer == null) {
                throw NonExistentCustomerException(id)
            }
            customer
        }
    }

}