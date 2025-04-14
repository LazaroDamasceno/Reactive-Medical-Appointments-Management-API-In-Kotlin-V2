package com.api.v2.customers.exceptions

class NonExistentCustomerException(id: String)
    : RuntimeException("Customer whose id is $id was not found.")