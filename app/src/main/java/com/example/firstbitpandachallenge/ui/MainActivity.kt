package com.example.firstbitpandachallenge.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstbitpandachallenge.R
import com.example.firstbitpandachallenge.adapter.AssetWalletsAdapter
import com.example.firstbitpandachallenge.databinding.ActivityMainBinding
import com.example.firstbitpandachallenge.model.AssetWalletDataItem
import com.example.firstbitpandachallenge.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var activityMainBinding : ActivityMainBinding

    // Click listener for displaying a wallet detail activity
    val onWalletClicked: (id: String, balance: String) -> Unit = { id, balance ->
        (viewModel as MainActivityViewModel).walletClicked(id, balance)
    }

    private val adapter = AssetWalletsAdapter(onWalletClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        linearLayoutManager = LinearLayoutManager(this)

        subscribe()

    }

    override fun onResume() {
        super.onResume()
        (viewModel as MainActivityViewModel).init()

        activityMainBinding.viewmodel = viewModel as MainActivityViewModel
        activityMainBinding.lifecycleOwner = this
        activityMainBinding.executePendingBindings()

        initRecyclerView()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                showFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        transaction_recycler_view.layoutManager = linearLayoutManager
        transaction_recycler_view.setHasFixedSize(true)
        transaction_recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        transaction_recycler_view.adapter = adapter

        (viewModel as MainActivityViewModel).onWalletsList().observe(this, androidx.lifecycle.Observer { getWalletsList(it) })
    }

    /**
     * Show the dialog to filter the wallets list.
     */
    private fun showFilterDialog() {
        val filterOptions = arrayOf(
            getString(R.string.main_screen_filter_dialog_all),
            getString(R.string.main_screen_filter_dialog_fiat),
            getString(R.string.main_screen_filter_dialog_cryptocoin),
            getString(R.string.main_screen_filter_dialog_metal)
        )

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.main_screen_filter_dialog_title))

        builder.setItems(filterOptions) { _, which ->
            (viewModel as MainActivityViewModel).setFilterTypeSelected(which)
        }
        builder.setNegativeButton(getString(R.string.main_screen_filter_dialog_button), null)
        builder.show()
    }

    /**
     * Set the new wallet list to the adapter.
     */
    private fun getWalletsList(list: ArrayList<AssetWalletDataItem>) {
        adapter.setData(list)
    }

}
