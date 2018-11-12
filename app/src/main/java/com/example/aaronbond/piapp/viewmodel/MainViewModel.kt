package com.example.aaronbond.piapp.viewmodel

import android.annotation.SuppressLint
import android.databinding.ObservableField
import android.util.Log
import com.example.aaronbond.piapp.PiService

class MainViewModel {

    val estimate = ObservableField("")
    val durationSeconds = ObservableField("")

    private lateinit var binder: PiService.ServiceBinder

    fun startEstimation() {
        binder.resumeEstimation()
    }

    fun pauseEstimation() {
        binder.pauseEstimation()
    }

    fun stopEstimation() {
        binder.stopEstimation()
    }

    @SuppressLint("CheckResult")
    fun setServiceBinder(serviceBinder: PiService.ServiceBinder) {
        binder = serviceBinder
        binder.getPiObservable().subscribe(
            {
                estimate.set(it.estimate.toString())
                durationSeconds.set("${it.durationSeconds} Seconds")
            },
            { Log.e("ERROR", it.message) }
        )
    }
}