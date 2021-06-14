package me.aleatitus.simple_banking_system_api

import me.aleatitus.simple_banking_system_api.exceptions.NonexistentAccountException

class BankingSystemDao (private val accounts: MutableMap<String, Account> = mutableMapOf()) {
    fun reset() {
        accounts.clear()
    }

    fun getAccountBalanceById(accountId: String): Int {
        return (accounts[accountId] ?: throw NonexistentAccountException("")).balance
    }
}