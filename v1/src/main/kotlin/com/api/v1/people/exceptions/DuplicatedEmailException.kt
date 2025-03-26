package com.api.v1.people.exceptions

class DuplicatedEmailException: RuntimeException("Given email is already in use.")