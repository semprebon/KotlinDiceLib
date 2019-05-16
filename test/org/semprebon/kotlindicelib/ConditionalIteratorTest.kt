package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Test for ConditionalIterator
 */
class ConditionalIteratorTest {

    @Test
    fun iteratorFiltersOutput() {
        val list = listOf(2, 3, 5, 8, 12)
        val iterator = FilterIterator(list.iterator(), { (4..10).contains(it) })
        assertTrue(iterator.hasNext())
        assertEquals(5, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(8, iterator.next())
        assertFalse(iterator.hasNext())
    }
}

