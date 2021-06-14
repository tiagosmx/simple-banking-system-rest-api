package me.aleatitus.simple_banking_system_api.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import me.aleatitus.simple_banking_system_api.utils.jsonBuilder

@Serializable
data class TransferResponse (
    val origin: Account, val destination: Account
){
    fun toJson(): String{
        return jsonBuilder.encodeToString(this)
    }
    companion object {
        fun fromJson(json: String): TransferResponse {
            return jsonBuilder.decodeFromString(json)
        }
    }
}