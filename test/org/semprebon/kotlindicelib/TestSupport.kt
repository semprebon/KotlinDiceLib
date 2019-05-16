package org.semprebon.kotlindicelib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.*

/**
 * Common stuff for supporting tests
 */
interface TestSupport {
    companion object {
        const val ERR = 0.0001
    }

    /**
     * Converts a rolls of dice roll into a map representing a histogram of counts for each combination
     */
    fun canonize(rolls: Iterable<Iterable<Int>>): Map<List<Int>, Int> {
        return rolls.map { it.toList() }
                .fold(HashMap(),
                    { result, roll ->
                        val oldValue = result[roll]
                        if (oldValue == null) {
                            result[roll] = 1
                        } else {
                            result[roll] = oldValue + 1
                        }
                        result
                    })
    }

    fun assertSameElements(expected: Iterable<Iterable<Int>>, actual: Iterable<Iterable<Int>>) {
        assertEquals(canonize(expected), canonize(actual))
    }

    fun verifyMemoryUse(limit: Int, code: () -> Any): Any {
        val runtime = Runtime.getRuntime()
        runtime.gc()
        val beforeMemory = runtime.totalMemory() - runtime.freeMemory()
        val result = code()
        runtime.gc()
        val usedMemmory = (runtime.totalMemory() - runtime.freeMemory()) - beforeMemory
        assertTrue(usedMemmory < limit, "Memory use of $usedMemmory exceeded limit of $limit")
        return result
    }

    fun verifySpeed(secLimit: Double, code: () -> Any): Any {
        val limit = (secLimit * 10000*1000*1000).toInt()
        val reps = 1

        var result = code()
        val start = System.nanoTime()
        (1..reps).forEach { result = code() }

        val elapsed = (System.nanoTime() - start) / reps
        assertTrue(elapsed < limit, "Used $elapsed nsecs; exceeded limit of $limit")
        return result
    }
}