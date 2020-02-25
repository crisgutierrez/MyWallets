package com.example.firstbitpandachallenge.viewmodel

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.firstbitpandachallenge.R
import com.example.firstbitpandachallenge.model.Currency
import com.example.firstbitpandachallenge.remote.DummyWebService
import com.example.firstbitpandachallenge.repository.Repository
import com.example.firstbitpandachallenge.utils.round

class DetailActivityViewModel : BaseViewModel() {

    var title = MutableLiveData<String>()

    private val repository = Repository(DummyWebService())

    var balanceData = MutableLiveData<String>()
    var priceData = MutableLiveData<String>()
    var priceDataVisibility = MutableLiveData<Int>()

    private lateinit var walletItem: Currency
    private lateinit var balance: String

    /**
     * Initialize the viewModel.
     */
    fun init(id: String, balance: String, context: Context) {
        walletItem = repository.findById(id)!!
        this.balance = balance

        balanceData.value = context.getString(R.string.detail_screen_balance, balance, walletItem.symbol)

        priceData.value = context.getString(R.string.detail_screen_price, walletItem.price.round().toString())

        // If the price wasn't setted (by default price is 0 so if it is not setted it will be 0)
        // we hide the price textView.
        priceDataVisibility.value = if (walletItem.price > 0) {
            View.VISIBLE
        } else {
            View.GONE
        }

        title.value = walletItem.name
    }

    /**
     * Return the logo uri of the current [walletItem].
     */
    fun getLogoUri() : Uri {
        return Uri.parse(
            walletItem.logo
        )
    }

    /**
     * Since this feature is not implemented yet when Buy button is clicked we display a toast.
     */
    fun onBuyButtonClicked() {
        showToast(R.string.detail_screen_feature_no_implemented)
    }

    /**
     * Since this feature is not implemented yet when Sell button is clicked we display a toast.
     */
    fun onSellButtonClicked() {
        showToast(R.string.detail_screen_feature_no_implemented)
    }

    fun onTitle() = title
}