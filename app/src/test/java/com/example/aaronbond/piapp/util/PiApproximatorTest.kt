package com.example.aaronbond.piapp.util

import com.example.aaronbond.piapp.model.PiApproximator
import org.junit.Test

class PiApproximatorTest {

    @Test
    fun `Approximator returns a known approximate after 100 iterations of the algorithm`() {
        val approximator = PiApproximator(100, 1)

        val testObserver = approximator.output.test()
        approximator.startEstimation()

        testObserver.apply {
            awaitTerminalEvent()
            assertNoErrors()
            assertValueCount(101)
            assertValueAt(100) { value -> value.estimate == 3.1514934010709914 }
        }
    }
}