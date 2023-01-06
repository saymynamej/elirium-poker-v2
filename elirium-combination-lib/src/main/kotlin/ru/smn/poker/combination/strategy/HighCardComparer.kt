package ru.smn.poker.combination.strategy

import org.springframework.stereotype.Service
import ru.smn.poker.combination.data.Combination

@Service
class HighCardComparer : Comparer {
    override fun compare(combinations: List<Combination>): List<Combination> {
        return listOf(combinations.maxBy { combination ->
            combination.cards.sumOf { card -> card.powerAsInt }
        })
    }
}