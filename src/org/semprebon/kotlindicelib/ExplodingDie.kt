package org.semprebon.kotlindicelib

/**
 * Models an exploding die where, if the max value is rolled, the die is rolled
 * again and added
 */
class ExplodingDie(sides: Int) : SimpleDie(sides) {
    val max_rolls = 3
    override val size = sides
    override val min = 1
    override val max = max_rolls * sides

    override val expectedValue by lazy { ((sides + 1) * sides).toDouble() / (2.0 * (sides-1)) }

    override fun range(minProbability: Double): IntRange {
        var p = baseProbability.value
        var rangeMax = sides-1
        while (p * baseProbability.value > minProbability) {
            p *= baseProbability.value
            rangeMax += sides
        }
        return min..rangeMax
    }

    override fun roll(): RollResult {
        var value = super.roll().value
        var count = 1
        while (value == size && count < 10) {
            count += 1
            value = super.roll().value
        }
        return RollResult(listOf(size*(count-1) + value), this)
    }

    override fun probToRoll(target: Int): Probability {
        if (target < min) return Probability.NEVER
        if (target < size) return baseProbability
        if (target == size) return Probability.NEVER
        return baseProbability.and(probToRoll(target - size))
    }

    override fun probToBeat(target: Int): Probability {
        if (target <= size) return super.probToBeat(target)
        return baseProbability.and(probToBeat(target - size))
    }

    override fun toString(): String {
        return "d${sides}!"
    }
}