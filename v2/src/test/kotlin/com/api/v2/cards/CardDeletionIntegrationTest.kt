package com.api.v2.cards

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CardDeletionIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    @Order(1)
    fun testSuccessful() {
        val id = ""
        webTestClient
            .delete()
            .uri("api/v2/cards/$id")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

    @Test
    @Order(2)
    fun testUnsuccessful() {
        val id = ""
        webTestClient
            .delete()
            .uri("api/v2/cards/$id")
            .exchange()
            .expectStatus()
            .is4xxClientError
    }

}