package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.semprebon.kotlindicelib.TestSupport.Companion.ERR

/**
 * Tests ExplodingDie
 */
class ExplodingDieTest {

    fun assertProbabilityEqual(expected: Double, actual: Probability) {
        ProbabilityTest.assertNearlyEqual(expected, actual.value)
    }

    val die = ExplodingDie(10)

    @Test
    fun probToRoll() {
        assertProbabilityEqual(1.0 / 10.0, die.probToRoll(9))
        assertProbabilityEqual(0.0, die.probToRoll(0))
        assertProbabilityEqual(1.0 / 100.0, die.probToRoll(11))
    }

    @Test
    fun probToBeat() {
        assertProbabilityEqual(2.0 / 10.0, die.probToBeat(9))
        assertProbabilityEqual(1.0 / 100.0, die.probToBeat(20))
        assertProbabilityEqual(1.0, die.probToBeat(1))
    }

    @Test
    fun roll() {
        for (i in 1..10) {
            val r = die.roll()
            assertTrue(r.value >= 1, "$r is equal or greater than 1")
        }
    }

    @Test
    fun range() {
        assertEquals(1..19, die.range(0.01))
    }

    @Test
    fun expectedValueIsCorrect() {
        assertEquals(110.0/18.0, die.expectedValue, ERR)
    }

    @Test
    fun equalsWithEqualDice() {
        assertEquals(ExplodingDie(10), die)
    }

    @Test
    fun equalsWithDifferentTypeDice() {
        assertNotEquals(die, SimpleDie(10))
    }

    @Test
    fun hashCodeReturnsDifferentValues() {
        assertEquals(ExplodingDie(10).hashCode(), die.hashCode())
        assertNotEquals(ExplodingDie(12), die.hashCode())
    }

}