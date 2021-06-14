package me.aleatitus.simple_banking_system_api

import io.javalin.http.Context
import io.javalin.http.Handler
import me.aleatitus.simple_banking_system_api.exceptions.NonexistentAccountException

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


}