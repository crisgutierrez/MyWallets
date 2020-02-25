package com.example.firstbitpandachallenge.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstbitpandachallenge.model.ActivityModel

open class BaseViewModel : ViewModel() {

    var startActivity = MutableLiveData<ActivityModel>()
    var showToast = MutableLiveData<Int>()
    var progress = MutableLiveData<Int>()
    var closeActivity = MutableLiveData<Unit>()

    init {
        hideLoading()
    }

    fun showLoading() {
        progress.value = View.VISIBLE
    }

    fun hideLoading() {
        progress.value = View.GONE
    }

    fun closeActivity() {
        closeActivity.value = Unit
    }

    fun showActivity(activityModel: ActivityModel) {
        startActivity.value = activityModel
    }

    fun showToast(text: Int) {
        showToast.value = text
    }

    fun onStartActivity() = startActivity
    fun onShowToast() = showToast
    fun onCloseActivity() = closeActivity

}