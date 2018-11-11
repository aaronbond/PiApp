package com.example.aaronbond.piapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.aaronbond.piapp.model.PiApproximator
import io.reactivex.subjects.PublishSubject

class PiService : Service() {

    private val estimator = PiApproximator()
    private val binder = ServiceBinder(estimator.output, estimator, this)

    override fun onBind(intent: Intent): IBinder {
        estimator.startEstimation()
        return binder
    }

    class ServiceBinder(
        private val output: PublishSubject<PiApproximator.Progress>,
        private val approximator: PiApproximator,
        private val service: PiService
    ) : Binder() {
        fun getPiObservable() = output

        fun resumeEstimation() {
            approximator.resumeEstimation()
        }

        fun pauseEstimation() = approximator.pauseEstimation()
        fun stopEstimation() {
            approximator.stopEstimation()
            service.stopSelf()
        }
    }
}
