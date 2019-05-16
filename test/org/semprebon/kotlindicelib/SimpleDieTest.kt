package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.semprebon.kotlindicelib.TestSupport.Companion.ERR

/**
 * Test for SimpleDie
 */
class SimpleDieTest {
    fun assertProbabilityEqual(expected: Double, actual: Probability) {
        ProbabilityTest.assertNearlyEqual(actual.value, expected)
    }

    val d6 = SimpleDie(6)
    val d8 = SimpleDie(8)

    @Test
    fun probToRoll() {
        assertProbabilityEqual(1.0 / 6.0, d6.probToRoll(2))
        assertProbabilityEqual(0.0, d6.probToRoll(0))
        assertProbabilityEqual(0.0, d6.probToRoll(7))
    }

    @Test
    fun probToBeat() {
        assertProbabilityEqual(4.0 / 6.0, d6.probToBeat(3))
        assertProbabilityEqual(1.0, d6.probToBeat(1))
        assertProbabilityEqual(0.0, d6.probToBeat(7))
    }

    @Test
    fun probToRollOver() {
        assertProbabilityEqual(3.0 / 6.0, d6.probToRollOver(3))
        assertProbabilityEqual(1.0, d6.probToRollOver(0))
        assertProbabilityEqual(0.0, d6.probToRollOver(7))
    }

    @Test
    fun probToRollUnder() {
        assertProbabilityEqual(2.0 / 6.0, d6.probToRollUnder(3))
        assertProbabilityEqual(0.0, d6.probToRollUnder(1))
        assertProbabilityEqual(1.0, d6.probToRollUnder(7))
    }

    @Test
    fun range() { assertEquals(d8.range(), 1..8) }

    @Test
    fun roll() {
        for (i in 1..10) {
            val r = d6.roll().value
            assertTrue(r in 1..6, "$r is between 1 and 6")
        }
    }

    @Test
    fun expectedValueIsAverage() {
        assertEquals(3.5, d6.expectedValue, ERR)
    }

    @Test
    fun equalsWithEqualDice() {
        assertEquals(SimpleDie(6), d6)
    }

    @Test
    fun equalsWithDifferentSizeDice() {
        assertNotEquals(SimpleDie(8), d6)
    }

    @Test
    fun equalsWithDifferentTypeDice() {
        assertNotEquals(d6, ExplodingDie(6))
    }

    @Test
    fun equalsWithNull() {
        assertNotEquals(SimpleDie(8), null)
    }

    @Test
    fun equalsWithNonDie() {
        assertNotEquals(SimpleDie(8), 6)
    }

    @Test
    fun hashCodeReturnsDifferentValues() {
        assertEquals(SimpleDie(6).hashCode(), d6.hashCode())
        assertNotEquals(SimpleDie(8), d6.hashCode())
    }
}