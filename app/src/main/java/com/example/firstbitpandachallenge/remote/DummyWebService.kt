package com.example.firstbitpandachallenge.remote

import com.example.firstbitpandachallenge.model.*

class DummyWebService {

    fun getCryptoWallets(): List<AssetsWallet> = DummyData.dummyCryptoWalletList

    fun getMetalWallets(): List<AssetsWallet> = DummyData.dummyMetalWalletList

    fun getAssetWallets(): List<AssetsWallet> = getFiatWallets() + getCryptoWallets() + getMetalWallets()

    fun getFiatWallets(): List<AssetsWallet> = DummyData.dummyEURWallet

    fun getCryptocoins(): List<Cryptocoin> = DummyData.cryptocoins

    fun getMetals(): List<Metal> = DummyData.metals

    fun getFiats(): List<Fiat> = DummyData.fiats

    fun getCurrencies(): List<Currency> = getFiats() + getCryptocoins() + getMetals()

}