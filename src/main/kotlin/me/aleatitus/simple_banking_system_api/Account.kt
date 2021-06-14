package me.aleatitus.simple_banking_system_api

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import me.aleatitus.simple_banking_system_api.utils.jsonBuilder

@Serializable
data class Account (
    val id: String,
    val balance: Int
){
    companion object {
        fun fromJson(json: String): Account {
            return jsonBuilder.decodeFromString(json)
        }
    }

    fun toJson(): String{
        return jsonBuilder.encodeToString(this)
    }
}