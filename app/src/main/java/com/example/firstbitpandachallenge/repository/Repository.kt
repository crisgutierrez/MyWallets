package com.example.firstbitpandachallenge.repository

import com.example.firstbitpandachallenge.model.AssetsWallet
import com.example.firstbitpandachallenge.remote.DummyWebService

class Repository(val webservice: DummyWebService) {


    fun getAssetWalletList() = webservice.getAssetWallets() as ArrayList<AssetsWallet>


    fun findById(id: String) = webservice.getCurrencies().find { it.id == id }


    fun findBySymbol(symbol: String) = webservice.getCurrencies().find { it.symbol == symbol }


    fun findByName(name: String) = webservice.getCurrencies().find { it.name == name }


}