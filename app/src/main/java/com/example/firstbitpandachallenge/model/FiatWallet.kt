package com.example.firstbitpandachallenge.model

class FiatWallet (
    val fiats: List<Fiat> = emptyList(),
    override val id: String = "",
    override var balance: Double = 0.0,
    override val deleted: Boolean = false,
    override val name: String = "",
    val fiatId: String = "1",
    override val isDefault: Boolean = false
) : AssetsWallet(type = 1)