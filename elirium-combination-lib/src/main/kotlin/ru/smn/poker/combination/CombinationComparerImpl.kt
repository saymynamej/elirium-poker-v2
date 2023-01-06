package ru.smn.poker.combination

import org.springframework.stereotype.Service
import ru.smn.poker.combination.data.Combination
import ru.smn.poker.combination.data.CombinationType
import ru.smn.poker.combination.strategy.ComparerAssistant
@Service
class CombinationComparerImpl(
    private val comparerAssistant: ComparerAssistant = ComparerAssistant(),
) : CombinationComparer {
    override fun compare(
        combinationType: CombinationType,
        combinations: List<Combination>,
    ): List<Combination> {
        if (combinations.size == 1) return combinations
        return comparerAssistant.compare(combinationType, combinations)
    }
}