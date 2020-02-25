package com.example.firstbitpandachallenge.model

data class Cryptocoin (
    val precision: Int = 4,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val price: Double = 0.0,
    override val logo: String = ""
    ): Currency(id)