package com.api.v2.cards

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CreditCardRegistrationIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun testSuccessful() {
        webTestClient
            .post()
            .uri("api/v2/cards/credit-card/369/2025-12-12")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

}