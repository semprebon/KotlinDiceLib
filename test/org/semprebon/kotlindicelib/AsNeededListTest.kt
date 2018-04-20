package org.semprebon.kotlindicelib

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.semprebon.kotlindicelib.AsNeededList

/**
 * Test for AsNeededList
 */
class AsNeededListTest {

    val baseList = listOf(1,2,3,6,10,15,25)

    @Test
    fun getsFirstElement() {
        val list = AsNeededList(baseList.iterator())
        assertEquals(1, list[0])
    }

    @Test
    fun getLaterElement() {
        val list = AsNeededList(baseList.iterator())
        assertEquals(10, list[4])
    }

    @Test
    fun indexOf() {
        val list = AsNeededList(baseList.iterator())
        assertEquals(2, list.indexOf(3))
    }
}