package com.example.firstbitpandachallenge.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.example.firstbitpandachallenge.model.*
import com.example.firstbitpandachallenge.remote.DummyWebService
import com.example.firstbitpandachallenge.repository.Repository
import com.example.firstbitpandachallenge.ui.DetailActivity
import com.example.firstbitpandachallenge.ui.WALLET_DETAIL_BALANCE
import com.example.firstbitpandachallenge.ui.WALLET_DETAIL_ID
import com.example.firstbitpandachallenge.utils.filterByType

class MainActivityViewModel: BaseViewModel() {

    var wallets = arrayListOf<AssetsWallet>()

    var walletsList = MutableLiveData<ArrayList<AssetWalletDataItem>>()

    private val repository = Repository(DummyWebService())

    private var type = 0

    /**
     * Initialize the viewModel.
     */
    fun init() {
        showLoading()
        wallets = repository.getAssetWalletList()
        wallets.removeAll { it.deleted }
        setFilterTypeSelected(type)
    }

    /**
     * Sort the list by [type] and balance.
     */
    fun sortWalletList(wallets: List<AssetsWallet>) = wallets.sortedWith(compareBy({ it.type }, { it.balance }))

    /**
     * We mapped the current list item to have a list with an item (AssetWalletDataItem) that only
     * only have the information that is require. To do that first we sorted the list and then
     * we get the data that is not available in the current item such as the id or the logo,
     * once we have all the data we create a new AssetWalletDataItem.
     */
    private fun mapWalletList(wallets: List<AssetsWallet>): ArrayList<AssetWalletDataItem> {
        val assetWalletDataItemList = arrayListOf<AssetWalletDataItem>()
        val sortedList = sortWalletList(wallets)
        sortedList.forEach {

            var name = it.name

            val id = when (it) {
                is CryptocoinWallet -> it.cryptocoinId
                is MetalWallet -> {
                    // In case of Metal Wallet we display the name of the item.
                    name = repository.findById(it.metalId)?.name ?: it.name
                    it.metalId
                }
                is FiatWallet -> it.fiatId
                else -> ""
            }

            val logo = repository.findById(id)?.logo ?: ""

            assetWalletDataItemList.add(
                AssetWalletDataItem(
                    id,
                    name,
                    logo,
                    it.balance
                )
            )
        }
        return assetWalletDataItemList
    }

    /**
     * Set the [type] selected and filter the list using that [type].
     */
    fun setFilterTypeSelected(type: Int) {
        this.type = type
        val filteredList = wallets.filterByType(type) { it.type == type }
        showWalletList(mapWalletList(filteredList))
    }

    /**
     * Show the new wallet list.
     */
    private fun showWalletList(wallets: ArrayList<AssetWalletDataItem>) {
        hideLoading()
        walletsList.value = wallets
    }

    /**
     * When an item from the wallet list is clicked we open the detail screen of that item.
     */
    fun walletClicked(id: String, balance: String) {
        val extra= bundleOf(Pair(WALLET_DETAIL_ID, id))
        extra.putString(WALLET_DETAIL_BALANCE, balance)
        showActivity(ActivityModel(DetailActivity::class.java, extra))
    }

    fun onWalletsList() = walletsList
}