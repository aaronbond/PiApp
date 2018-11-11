package com.example.aaronbond.piapp.model

import com.example.aaronbond.piapp.util.isEven
import com.google.common.base.Stopwatch
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class PiApproximator(private val iterations : Int = 100000000,
                     private val reportRate: Int = 10) {

    val output = PublishSubject.create<Progress>()

    private var isRunning = false
    private var total = 0.0
    private val stopWatch = Stopwatch.createUnstarted()

    private lateinit var job: Job

    fun startEstimation() {
        isRunning = true
        stopWatch.reset()
        stopWatch.start()

        job = GlobalScope.launch {
            (0..iterations).forEach {
                if (!isActive) return@forEach
                while (!isRunning) delay(100)
                total += (if (it.isEven()) 1.0 else -1.0) / (1 + 2 * it)
                if (it % reportRate == 0) output.onNext(
                    Progress(
                        total * 4,
                        stopWatch.elapsed(TimeUnit.SECONDS)
                    )
                )
            }
            output.onComplete()
        }
    }

    fun stopEstimation() {
        job.cancel()
        if (stopWatch.isRunning) stopWatch.stop()
        total = 0.0
    }

    fun pauseEstimation() {
        isRunning = false
        if (stopWatch.isRunning) stopWatch.stop()
    }

    fun resumeEstimation() {
        isRunning = true
        if (!stopWatch.isRunning) stopWatch.start()
        if (!job.isActive) startEstimation()
    }

    data class Progress(val estimate: Double, val timeTaken: Long)
}