package me.aleatitus.simple_banking_system_api.controllers

import io.javalin.http.Context
import io.javalin.http.Handler
import me.aleatitus.simple_banking_system_api.exceptions.NonexistentAccountException
import me.aleatitus.simple_banking_system_api.models.Event
import me.aleatitus.simple_banking_system_api.services.BankingSystemDao
import me.aleatitus.simple_banking_system_api.utils.HTTPContentTypes

class BankingSystemController(private val bankingSystemDao: BankingSystemDao) {
    val reset = Handler { ctx: Context ->
        bankingSystemDao.reset()
        ctx.status(200)
        ctx.html("OK")
    }

    val getAccountBalance = Handler { ctx: Context ->
        try {
            val accountId = ctx.queryParam("account_id")
            val balance = bankingSystemDao.getAccountBalanceById(
                accountId ?: throw NonexistentAccountException("")
            )
            ctx.status(200)
            ctx.html(balance.toString())
        } catch (e: NonexistentAccountException){
            ctx.status(404)
            ctx.html("0")
        }
    }

    val event = Handler { ctx: Context ->
        try{
            when(val event = Event.fromJson(ctx.body())){
                is Event.Deposit -> {
                    val deposit = bankingSystemDao.deposit(event)
                    ctx.status(201)
                    ctx.contentType(HTTPContentTypes.JSON.value)
                    ctx.result(deposit.toJson())
                }
                is Event.Withdraw -> {
                    val withdraw = bankingSystemDao.withdraw(event)
                    ctx.status(201)
                    ctx.contentType(HTTPContentTypes.JSON.value)
                    ctx.result(withdraw.toJson())
                }
                is Event.Transfer -> {
                    val transfer = bankingSystemDao.transfer(event)
                    ctx.status(201)
                    ctx.contentType(HTTPContentTypes.JSON.value)
                    ctx.result(transfer.toJson())
                }
            }
        } catch (e: NonexistentAccountException){
            ctx.status(404)
            ctx.html("0")
        }

    }


}