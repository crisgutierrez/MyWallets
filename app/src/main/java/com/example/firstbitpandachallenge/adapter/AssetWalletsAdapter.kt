package com.example.firstbitpandachallenge.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstbitpandachallenge.R
import com.example.firstbitpandachallenge.utils.inflate
import com.example.firstbitpandachallenge.model.AssetWalletDataItem
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.asset_wallet_item.view.*

class AssetWalletsAdapter(val onWalletClickedListener: (id: String, balance: String) -> Unit) :
    RecyclerView.Adapter<AssetWalletsAdapter.AssetWalletsHolder>()  {
    private var assetWalletsList = ArrayList<AssetWalletDataItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssetWalletsHolder {
        val inflatedView = parent.inflate(R.layout.asset_wallet_item, false)
        return AssetWalletsHolder(inflatedView, onWalletClickedListener)

    }

    override fun getItemCount() = assetWalletsList.size

    override fun onBindViewHolder(holder: AssetWalletsHolder, position: Int) {
        val assetWalletItem = assetWalletsList[position]
        holder.bindBalance(assetWalletItem)
    }

    fun setData(list: ArrayList<AssetWalletDataItem>) {
        assetWalletsList = list
        notifyDataSetChanged()
    }


    /**
     * Item view holder class
     */
    class AssetWalletsHolder(
        v: View,
        private val onWalletClickedListener: (id: String, balance: String) -> Unit
    ) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private lateinit var assetsWallet: AssetWalletDataItem

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            onWalletClickedListener.invoke(assetsWallet.id, assetsWallet.balance.toString())
        }

        fun bindBalance(assetsWallet: AssetWalletDataItem) {
            this.assetsWallet = assetsWallet

            view.asset_wallet_icon.loadUrl(assetsWallet.logo)
            view.asset_wallet_name.text = assetsWallet.name
            view.asset_wallet_balance.text = assetsWallet.balance.toString()
        }


        /**
         * Load the image using the [url] passed by param.
         */
        private fun ImageView.loadUrl(url: String) {
            GlideToVectorYou
                .init()
                .with(this.context)
                .setPlaceHolder(R.drawable.ic_wallet, R.drawable.ic_wallet)
                .load(Uri.parse(url), this)
        }
    }

}
