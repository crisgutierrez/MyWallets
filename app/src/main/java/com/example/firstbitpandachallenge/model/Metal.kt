package com.example.firstbitpandachallenge.model

data class Metal (
    val precision: Int = 3,
    override var name : String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val price: Double = 0.0,
    override val logo: String = ""
) : Currency()