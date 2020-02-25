package com.example.firstbitpandachallenge.model

data class Fiat (
    var precision : Int = 2,
    override var name : String = "",
    override val symbol: String = "",
    override val id: String = "",
    override var logo : String = ""
) : Currency()