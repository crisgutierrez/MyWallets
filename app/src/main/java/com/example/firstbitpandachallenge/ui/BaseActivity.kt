package com.example.firstbitpandachallenge.ui

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.firstbitpandachallenge.model.ActivityModel
import com.example.firstbitpandachallenge.viewmodel.BaseViewModel

open class BaseActivity : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel

    fun subscribe() {
        viewModel.onStartActivity().observe(this, Observer { startActivityModel(it) })
        viewModel.onShowToast().observe(this, Observer { showToast(it) })
        viewModel.onCloseActivity().observe(this, Observer { closeActivity() })
    }

    private fun startActivityModel(activityModel: ActivityModel) {
        val intent = Intent(this, activityModel.activity)

        activityModel.bundle?.let {
            activityModel.bundle.let { intent.putExtras(it) }
        }

        if (activityModel.resultCode > 0) {
            startActivityForResult(intent, activityModel.resultCode)
        } else {
            startActivity(intent)
        }
    }

    private fun showToast(text: Int) {
        Toast.makeText(this, getString(text), Toast.LENGTH_LONG).show()
    }

    private fun closeActivity() {
        finish()
    }
}
