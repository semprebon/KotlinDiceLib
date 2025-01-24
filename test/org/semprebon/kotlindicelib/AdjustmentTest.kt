package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.semprebon.kotlindicelib.Probability

/**
 * Test for Adjustment
 */
class AdjustmentTest {

    val adjustment = Adjustment(3)

    fun assertProbabilityEqual(expected: Double, actual: Probability) {
        ProbabilityTest.assertNearlyEqual(expected, actual.value)
    }

    @Test
    fun probToRoll() {
        assertProbabilityEqual(1.0, adjustment.probToRoll(3))
        assertProbabilityEqual(0.0, adjustment.probToRoll(2))
    }

    @Test
    fun probToBeat() {
        assertProbabilityEqual(1.0, adjustment.probToBeat(3))
        assertProbabilityEqual(0.0, adjustment.probToBeat(4))
    }

    @Test
    fun roll() {
        for (i in 1..10) {
            val r = adjustment.roll()
            assertEquals(3, r.value)
        }
    }

    @Test
    fun range() {
        assertEquals(3..3, adjustment.range(0.01))
    }

    @Test
    fun expectedValueIsCorrect() {
        assertEquals(3.0, adjustment.expectedValue, TestSupport.ERR)
    }
}