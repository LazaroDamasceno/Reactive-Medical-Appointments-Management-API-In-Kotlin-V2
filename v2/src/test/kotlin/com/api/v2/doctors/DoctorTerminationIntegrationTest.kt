package com.api.v2.doctors

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DoctorTerminationIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    fun testSuccessfulTermination() {
        webTestClient
            .patch()
            .uri("api/v2/doctors/12345678/CA/termination")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}