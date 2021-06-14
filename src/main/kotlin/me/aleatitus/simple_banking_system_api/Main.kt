package me.aleatitus.simple_banking_system_api

import io.javalin.Javalin
import me.aleatitus.simple_banking_system_api.utils.HTTPContentTypes

fun main(){
    startApi()
}

fun startApi(){
    val bankingSystemDao = BankingSystemDao()
    val bankingSystemController = BankingSystemController(bankingSystemDao)

    val app = Javalin.create { config ->
        config.defaultContentType = HTTPContentTypes.JSON.value
        config.enableCorsForAllOrigins()
    }.start(7000)

    app.post("reset",bankingSystemController.reset)
    app.get("balance", bankingSystemController.getAccountBalance)
    app.post("event", bankingSystemController.event)

}
