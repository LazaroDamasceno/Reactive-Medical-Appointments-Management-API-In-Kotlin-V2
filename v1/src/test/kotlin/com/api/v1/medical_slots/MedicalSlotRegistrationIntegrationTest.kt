package com.api.v1.medical_slots

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MedicalSlotRegistrationIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Order(1)
    @Test
    fun testSuccessfulMedicalSlot() {
        val licenseNumber = "12345678"
        val state = "CA"
        val availableAt = LocalDateTime.of(2025,12,12,12,30,30)
        webTestClient
            .post()
            .uri("api/v1/medical-slots/${licenseNumber}/${state}/${availableAt}")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}