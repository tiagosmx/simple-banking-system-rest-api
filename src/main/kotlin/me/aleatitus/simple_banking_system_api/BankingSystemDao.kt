package me.aleatitus.simple_banking_system_api

import me.aleatitus.simple_banking_system_api.exceptions.NonexistentAccountException
import me.aleatitus.simple_banking_system_api.models.*

class BankingSystemDao (private val accounts: MutableMap<String, Account> = mutableMapOf()) {
    fun reset() {
        accounts.clear()
    }

    fun getAccountBalanceById(accountId: String): Int {
        return (accounts[accountId] ?: throw NonexistentAccountException("")).balance
    }

    fun deposit(deposit: Event.Deposit): DepositResponse {
        val account = this.accounts.getOrPut(deposit.destination) { Account(deposit.destination, 0) }
        val updatedAccount = Account(account.id, account.balance + deposit.amount)
        this.accounts[updatedAccount.id] = updatedAccount
        return DepositResponse(updatedAccount)
    }

    fun withdraw(withdraw: Event.Withdraw): WithdrawResponse {
        val account = this.accounts[withdraw.origin] ?: throw NonexistentAccountException("")
        val updatedAccount = Account(account.id, account.balance - withdraw.amount)
        this.accounts[updatedAccount.id] = updatedAccount
        return WithdrawResponse(updatedAccount)
    }

    fun transfer(transfer: Event.Transfer): TransferResponse {
        val accountOrigin = this.accounts[transfer.origin] ?: throw NonexistentAccountException("")
        val accountDestination = this.accounts.getOrPut(transfer.destination) { Account(transfer.destination, 0) }
        val updatedAccountOrigin = Account(accountOrigin.id, accountOrigin.balance - transfer.amount)
        val updatedAccountDestination = Account(accountDestination.id, accountDestination.balance + transfer.amount)
        this.accounts[updatedAccountDestination.id] = updatedAccountDestination
        this.accounts[updatedAccountOrigin.id] = updatedAccountOrigin
        return TransferResponse(updatedAccountOrigin, updatedAccountDestination)
    }
}