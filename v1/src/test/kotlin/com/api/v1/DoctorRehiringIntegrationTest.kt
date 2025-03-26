package com.api.v1

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DoctorRehiringIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    fun testSuccessfulRehiring() {
        webTestClient
            .patch()
            .uri("api/v1/doctors/12345678/CA/rehiring")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}