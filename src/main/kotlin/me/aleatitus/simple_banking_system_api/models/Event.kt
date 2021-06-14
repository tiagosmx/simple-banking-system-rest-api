package me.aleatitus.simple_banking_system_api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import me.aleatitus.simple_banking_system_api.utils.jsonBuilder

@Serializable
sealed class Event {
    @Serializable
    @SerialName("deposit")
    data class Deposit(val destination: String, val amount: Int) : Event()

    @Serializable
    @SerialName("withdraw")
    data class Withdraw (val origin: String, val amount: Int) : Event()

    @Serializable
    @SerialName("transfer")
    data class Transfer (val origin: String, val amount: Int, val destination: String) : Event()

    companion object {
        fun fromJson(json: String): Event {
            return jsonBuilder.decodeFromString(json)
        }
    }

    fun toJson(): String{
        return jsonBuilder.encodeToString(this)
    }
}
