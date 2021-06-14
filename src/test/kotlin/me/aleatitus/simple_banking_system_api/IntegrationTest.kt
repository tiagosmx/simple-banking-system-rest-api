package me.aleatitus.simple_banking_system_api

import kong.unirest.Unirest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntegrationTest {
    private val url = "http://localhost:7000"

    @Test
    fun `Starting Javalin`(){
        Assertions.assertDoesNotThrow { startApi() }
    }

    @Test
    fun `Testing reset`(){
        val r = Unirest.post("$url/reset").asString()
        Assertions.assertEquals(r.status,200)
    }
}