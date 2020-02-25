package com.example.firstbitpandachallenge.ui

import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.firstbitpandachallenge.R
import com.example.firstbitpandachallenge.databinding.ActivityDetailBinding
import com.example.firstbitpandachallenge.viewmodel.DetailActivityViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_detail.*

const val WALLET_DETAIL_ID = "WALLET_DETAIL_ID"
const val WALLET_DETAIL_BALANCE = "WALLET_DETAIL_BALANCE"

class DetailActivity : BaseActivity() {

    private lateinit var activityDetailBinding : ActivityDetailBinding

    private lateinit var id: String
    private lateinit var balance: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        id = intent.getStringExtra(WALLET_DETAIL_ID) ?: ""
        balance = intent.getStringExtra(WALLET_DETAIL_BALANCE) ?: ""

        viewModel = ViewModelProvider(this)[DetailActivityViewModel::class.java]

        subscribe()

        (viewModel as DetailActivityViewModel).onTitle().observe(this, androidx.lifecycle.Observer {title = it })
    }

    override fun onResume() {
        super.onResume()
        (viewModel as DetailActivityViewModel).init(id, balance, this)

        activityDetailBinding.viewmodel = viewModel as DetailActivityViewModel
        activityDetailBinding.lifecycleOwner = this
        activityDetailBinding.executePendingBindings()

        val uri = (viewModel as DetailActivityViewModel).getLogoUri()
        loadLogo(uri)
    }

    /**
     * Load the logo using the [uri] setted.
     */
    private fun loadLogo(uri: Uri) {
        GlideToVectorYou
            .init()
            .with(this)
            .setPlaceHolder(R.drawable.ic_wallet, R.drawable.ic_wallet)
            .load(uri, logo)
    }
}
