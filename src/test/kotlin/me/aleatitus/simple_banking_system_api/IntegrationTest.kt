package me.aleatitus.simple_banking_system_api

import kong.unirest.Unirest
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class IntegrationTest {
    private val url = "http://localhost:7000"

    /** Starting API and checking if no exception occurs.
     *
     */
    @Test
    @Order(1)
    fun `Starting Javalin`(){
        Assertions.assertDoesNotThrow { startApi() }
    }

    /** Reset state before starting tests
     * POST /reset
     * 200 OK
     */
    @Test
    @Order(2)
    fun `Reset state before starting tests`(){
        val r = Unirest.post("$url/reset").asString()
        Assertions.assertEquals(r.status,200)
    }

    /** Get balance for non-existing account
     * GET /balance?account_id=1234
     * 404 0
     */
    @Test
    @Order(3)
    fun `Get balance for non-existing account`(){

    }

    /** Create account with initial balance
     * POST /event {"type":"deposit", "destination":"100", "amount":10}
     * 201 {"destination": {"id":"100", "balance":10}}
     */
    @Test
    @Order(4)
    fun `Create account with initial balance`(){

    }

    /** Deposit into existing account
     * POST /event {"type":"deposit", "destination":"100", "amount":10}
     * 201 {"destination": {"id":"100", "balance":20}}
     */
    @Test
    @Order(5)
    fun `Deposit into existing account`(){

    }

    /** Get balance for existing account
     * GET /balance?account_id=100
     * 200 20
     */
    @Test
    @Order(6)
    fun `Get balance for existing account`(){

    }

    /** Withdraw from non-existing account
     * POST /event {"type":"withdraw", "origin":"200", "amount":10}
     * 404 0
     */
    @Test
    @Order(7)
    fun `Withdraw from non-existing account`(){

    }

    /** Withdraw from existing account
     * POST /event {"type":"withdraw", "origin":"100", "amount":5}
     * 201 {"origin": {"id":"100", "balance":15}}
     */
    @Test
    @Order(8)
    fun `Withdraw from existing account`(){

    }

    /** Transfer from existing account
     * POST /event {"type":"transfer", "origin":"100", "amount":15, "destination":"300"}
     * 201 {"origin": {"id":"100", "balance":0}, "destination": {"id":"300", "balance":15}}
     */
    @Test
    @Order(9)
    fun `Transfer from existing account`(){

    }
}