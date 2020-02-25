package com.example.firstbitpandachallenge.model

class MetalWallet(
    override val id: String = "",
    override var balance: Double = 0.0,
    override val deleted: Boolean = false,
    override val name: String = "",
    override val isDefault: Boolean = false,
    val metalId: String = "4"
) : AssetsWallet(type = 3) {
    val metals: List<Metal> = emptyList()
}