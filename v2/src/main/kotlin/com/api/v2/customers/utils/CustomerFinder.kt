package com.api.v2.customers.utils

import com.api.v2.customers.domain.exposed.Customer
import com.api.v2.customers.domain.CustomerRepository
import com.api.v2.customers.exceptions.NonExistentCustomerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class CustomerFinder(
    private val customerRepository: CustomerRepository
) {

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