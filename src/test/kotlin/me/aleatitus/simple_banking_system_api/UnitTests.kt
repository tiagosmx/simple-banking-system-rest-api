package me.aleatitus.simple_banking_system_api

import me.aleatitus.simple_banking_system_api.exceptions.NonexistentAccountException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class UnitTests {
    private val bankingSystemDao = BankingSystemDao()

    @Test
    @DisplayName("Reset")
    @Order(1)
    fun reset(){
        Assertions.assertDoesNotThrow { bankingSystemDao.reset() }
    }

    @Test
    @DisplayName("Get balance")
    @Order(2)
    fun getAccountBalance(){
        Assertions.assertThrows(NonexistentAccountException::class.java){bankingSystemDao.getAccountBalanceById("1234")}
    }
}