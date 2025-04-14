package com.api.v2.customers

import com.api.v2.common.Address
import com.api.v2.common.States
import com.api.v2.customers.domain.CustomerRepository
import com.api.v2.customers.dtos.CustomerRegistrationDto
import com.api.v2.customers.dtos.exposed.CustomerResponseDto
import com.api.v2.customers.services.CustomerRegistrationServiceImpl
import com.api.v2.people.dtos.PersonRegistrationDto
import com.api.v2.people.enums.Gender
import com.api.v2.people.exceptions.DuplicatedSsnException
import com.api.v2.people.services.exposed.PersonRegistrationService
import kotlinx.coroutines.test.runTest
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import kotlin.test.assertEquals

class CustomerRegistrationServiceImplTest {

    private val customerRepository: CustomerRepository = mock()
    private val personRegistrationService: PersonRegistrationService = mock()
    private val service = CustomerRegistrationServiceImpl(customerRepository, personRegistrationService)

    @Test
    @Order(1)
    fun `register should return created response with customer dto when registration is successful`() = runTest {
        val registrationDto = CustomerRegistrationDto(
            PersonRegistrationDto(
                "Leo",
                "",
                "Santos",
                LocalDate.of(2000,12,12),
                "123456789",
                "leosantos@mail.com",
                Gender.CIS_MALE,
                "1234567890"
            ),
            Address(
                States.CA,
                "LA",
                "Downtown",
                "90012"
            )
        )
        val actual = service.register(registrationDto).body!!::class
        val expected = CustomerResponseDto::class
        assertEquals(expected, actual)
    }

    @Test
    @Order(2)
    fun `register should return conflict response when a duplicated ssn is encountered`() = runTest {
        val registrationDto = CustomerRegistrationDto(
            PersonRegistrationDto(
                "Leo",
                "",
                "Santos",
                LocalDate.of(2000,12,12),
                "123456789",
                "leosantos@mail.com",
                Gender.CIS_MALE,
                "1234567890"
            ),
            Address(
                States.CA,
                "LA",
                "Downtown",
                "90012"
            )
        )
        assertThrows<DuplicatedSsnException> {
            service.register(registrationDto)
        }
    }

    @Test
    @Order(3)
    fun `register should return conflict response when a duplicated email is encountered`() = runTest {
        val registrationDto = CustomerRegistrationDto(
            PersonRegistrationDto(
                "Leo",
                "",
                "Santos",
                LocalDate.of(2000,12,12),
                "987654321",
                "leosantos@mail.com",
                Gender.CIS_MALE,
                "1234567890"
            ),
            Address(
                States.CA,
                "LA",
                "Downtown",
                "90012"
            )
        )
        assertThrows<DuplicatedSsnException> {
            service.register(registrationDto)
        }
    }
}