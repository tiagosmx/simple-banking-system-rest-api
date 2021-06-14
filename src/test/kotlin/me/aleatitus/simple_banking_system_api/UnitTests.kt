package me.aleatitus.simple_banking_system_api

import me.aleatitus.simple_banking_system_api.exceptions.NonexistentAccountException
import me.aleatitus.simple_banking_system_api.services.BankingSystemDao
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class UnitTests {
    private val bankingSystemDao = BankingSystemDao()

    @Test
    @Order(1)
    fun `Reset accounts`(){
        Assertions.assertDoesNotThrow { bankingSystemDao.reset() }
    }

    @Test
    @Order(2)
    fun `Get account 1234 balance`(){
        Assertions.assertThrows(NonexistentAccountException::class.java){bankingSystemDao.getAccountBalanceById("1234")}
    }
}