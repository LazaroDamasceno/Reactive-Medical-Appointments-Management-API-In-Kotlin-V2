package com.api.v2.customers

import com.api.v2.common.Address
import com.api.v2.common.States
import com.api.v2.customers.dtos.CustomerRegistrationDto
import com.api.v2.people.dtos.PersonRegistrationDto
import com.api.v2.people.enums.Gender
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CustomerRegistrationIntegrationTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	val registrationDto = CustomerRegistrationDto(
		PersonRegistrationDto(
			"Leo",
			"",
			"Santos",
			LocalDate.of(2000,12,12),
			"123456789",
			"leosantos@gmail.com",
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

	@Test
	@Order(1)
	fun testSuccessfulRegistration() {
		webTestClient
			.post()
			.uri("api/v2/customers")
			.bodyValue(registrationDto)
			.exchange()
			.expectStatus()
			.is2xxSuccessful
	}

	val registrationDtoWithDuplicatedSsn = CustomerRegistrationDto(
		PersonRegistrationDto(
			"Leo",
			"",
			"Santos",
			LocalDate.of(2000,12,12),
			"123456789",
			"leosantos@gmail.com",
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

	@Test
	@Order(2)
	fun testUnsuccessfulRegistration_Duplicated_Ssn() {
		webTestClient
			.post()
			.uri("api/v2/customers")
			.bodyValue(registrationDtoWithDuplicatedSsn)
			.exchange()
			.expectStatus()
			.is4xxClientError
	}

	val registrationDtoWithDuplicatedEmail = CustomerRegistrationDto(
		PersonRegistrationDto(
			"Leo",
			"",
			"Santos",
			LocalDate.of(2000,12,12),
			"987654321",
			"leosantos@gmail.com",
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

	@Test
	@Order(3)
	fun testUnsuccessfulRegistration_Duplicated_Email() {
		webTestClient
			.post()
			.uri("api/v2/customers")
			.bodyValue(registrationDtoWithDuplicatedEmail)
			.exchange()
			.expectStatus()
			.is4xxClientError
	}

}
