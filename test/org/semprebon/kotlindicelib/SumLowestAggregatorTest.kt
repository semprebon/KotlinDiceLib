package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Test for SumHighestAgregator
 */
class SumLowestAggregatorTest {

    val aggr = SumLowestAggregator(2)
    val dice = listOf(SimpleDie(6), SimpleDie(6), SimpleDie(8))
    val d2 = SimpleDie(2)

    @Test
    fun min() {
        assertEquals(2, aggr.min(dice))
    }

    @Test
    fun minWithEmptyList() {
        assertEquals(0, aggr.min(listOf()))
    }

    @Test
    fun max() {
        assertEquals(12, aggr.max(dice))
    }

    @Test
    fun maxWithEmptyList() {
        assertEquals(0, aggr.max(listOf()))
    }

    @Test
    fun aggregate() {
        assertEquals(4, aggr.aggregate(listOf(1, 3, 6)))
    }

    @Test
    fun aggregateWithEmptyList() {
        assertEquals(0, aggr.aggregate(listOf()))
    }

    @Test
    fun limitRangeToReasonableSizeWith2Pick1() {
        val aggr = SumLowestAggregator(1)
        assertEquals(listOf(1..2, 1..2),
                aggr.limitRanges(2..2, listOf(1..2, 1..2)))
    }

    @Test
    fun limitRangeToReasonableSize() {
        assertEquals(listOf(1..100, 2..3, 1..10),
                aggr.limitRanges(2..6, listOf(1..100, 2..3, 1..10)))
    }

    @Test
    fun limitRangesReasonablyWhenNoPossibleLimit() {
        assertNotNull(aggr.limitRanges(13..100, listOf(1..4, 1..4, 1..4)))
    }

    @Test
    fun probabilitiesForSumLowest() {
        val dice = DiceCombination(listOf(d2, d2), SumLowestAggregator(1))
        val probabilities = dice.probabilitiesByValue(1..2)
        assertEquals(0.75, probabilities[1])
        assertEquals(0.25, probabilities[2])
    }
}
