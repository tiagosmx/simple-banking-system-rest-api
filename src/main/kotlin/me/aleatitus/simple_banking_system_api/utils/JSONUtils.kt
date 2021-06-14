package me.aleatitus.simple_banking_system_api.utils

import kotlinx.serialization.json.Json

val jsonBuilder: Json = Json{
        ignoreUnknownKeys = false
        prettyPrint = false
    }