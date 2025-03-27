package com.api.v1.doctors

import com.api.v1.common.States
import com.api.v1.doctors.dtos.DoctorRegistrationDto
import com.api.v1.doctors.dtos.exposed.MedicalLicenseNumber
import com.api.v1.people.dtos.PersonRegistrationDto
import com.api.v1.people.enums.Gender
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
class DoctorRegistration1IntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    val registrationDto = DoctorRegistrationDto(
        MedicalLicenseNumber(
            "87654321",
            States.CA
        ),
        PersonRegistrationDto(
            "Leonard",
            "",
            "Smith",
            LocalDate.of(2000,12,12),
            "987654321",
            "leosmith@mail.com",
            Gender.CIS_MALE,
            "1234567890"
        )
    )

    @Order(1)
    @Test
    fun testSuccessfulRegistration() {
        webTestClient
            .post()
            .uri("api/v1/doctors")
            .bodyValue(registrationDto)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}