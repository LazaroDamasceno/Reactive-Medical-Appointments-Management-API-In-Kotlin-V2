package com.api.v2.doctors

import com.api.v2.common.States
import com.api.v2.doctors.dtos.DoctorRegistrationDto
import com.api.v2.doctors.dtos.exposed.MedicalLicenseNumber
import com.api.v2.people.dtos.PersonRegistrationDto
import com.api.v2.people.enums.Gender
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DoctorRegistration2IntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    val registrationDto = DoctorRegistrationDto(
        MedicalLicenseNumber(
            "12345678",
            States.CA
        ),
        PersonRegistrationDto(
            "Leo",
            "",
            "Santos",
            LocalDate.of(2000,12,12),
            "123456789",
            "leosantos@gmail.com",
            Gender.CIS_MALE,
            "1234567890"
        )
    )

    @Order(1)
    @Test
    fun testSuccessfulRegistration() {
        webTestClient
            .post()
            .uri("api/v2/doctors")
            .bodyValue(registrationDto)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}