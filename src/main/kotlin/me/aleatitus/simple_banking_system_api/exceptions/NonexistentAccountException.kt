package me.aleatitus.simple_banking_system_api.exceptions

data class NonexistentAccountException(override val message: String): Exception()