package com.example.firstbitpandachallenge.model

class CryptocoinWallet (
    override val id: String = "",
    override var balance: Double = 0.0,
    override val deleted: Boolean = false,
    override val name: String = "",
    override val isDefault: Boolean = false,
    val cryptocoinId: String = "1"
): AssetsWallet(type = 2)