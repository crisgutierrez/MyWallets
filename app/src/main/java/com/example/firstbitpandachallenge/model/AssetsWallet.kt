package com.example.firstbitpandachallenge.model

open class AssetsWallet(
    open val id: String = "",
    open var balance: Double = 0.0,
    open val deleted: Boolean = false,
    open val name: String = "",
    open val isDefault: Boolean = false,
    val type: Int = 0
) {

    /**
     * Reduce [balance] the [amount] setted.
     */
    fun reduceBalance(amount: Double) {
        balance.minus(amount)
    }

    /**
     * Increase [balance] the [amount] setted.
     */
    fun addBalance(amount: Double) {
        balance.plus(amount)
    }
}