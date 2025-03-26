package com.api.v1.customers

class NonExistentCustomerException(id: String)
    : RuntimeException("Customer whose id is $id was not found.")