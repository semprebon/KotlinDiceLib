package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Test for SumHighestAgregator
 */
class SumHighestAggregatorTest {

    val aggr = SumHighestAggregator(2)
    val dice = listOf(SimpleDie(6), SimpleDie(6), SimpleDie(8))

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
        assertEquals(14, aggr.max(dice))
    }

    @Test
    fun maxWithEmptyList() {
        assertEquals(0, aggr.max(listOf()))
    }

    @Test
    fun aggregate() {
        assertEquals(9, aggr.aggregate(listOf(1, 3, 6)))
    }

    @Test
    fun aggregateWithEmptyList() {
        assertEquals(0, aggr.aggregate(listOf()))
    }

    @Test
    fun limitRangeToReasonableSize() {
        assertEquals(listOf(1..4, 2..3, 1..4),
                aggr.limitRanges(2..6, listOf(1..100, 2..3, 1..10)))
    }
    @Test
    fun limitRangesReasonablyWhenNoPossibleLimit() {
        assertNotNull(aggr.limitRanges(13..100, listOf(1..4,1..4,1..4)))
    }
}