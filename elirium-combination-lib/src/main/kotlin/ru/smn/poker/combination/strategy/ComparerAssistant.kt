package ru.smn.poker.combination.strategy

import ru.smn.poker.combination.data.Combination
import ru.smn.poker.combination.data.CombinationType

class ComparerAssistant {
    fun compare(combinationType: CombinationType, combinations: List<Combination>): List<Combination> {
        val comparer = compares[combinationType] ?: throw RuntimeException()
        return comparer.compare(combinations)
    }

    companion object {
        private val compares: Map<CombinationType, Comparer> = mapOf(
            CombinationType.HIGH_CARD to HighCardComparer(),
            CombinationType.ONE_PAIR to PairCardComparer()
        )
    }
}