package me.aleatitus.simple_banking_system_api.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import me.aleatitus.simple_banking_system_api.utils.jsonBuilder

@Serializable
data class DepositResponse (
    val destination: Account
){
    fun toJson(): String{
        return jsonBuilder.encodeToString(this)
    }
    companion object {
        fun fromJson(json: String): DepositResponse {
            return jsonBuilder.decodeFromString(json)
        }
    }
}