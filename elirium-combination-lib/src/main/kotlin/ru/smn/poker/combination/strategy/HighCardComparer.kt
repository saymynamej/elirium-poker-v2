package ru.smn.poker.combination.strategy

import org.springframework.stereotype.Service
import ru.smn.poker.combination.data.Card
import ru.smn.poker.combination.data.Combination

@Service
class HighCardComparer : Comparer {
    override fun compare(combinations: List<Combination>): List<Combination> {
        val cards = combinations.map(Combination::getCards)
        for (i in 0..cards.size) {
            val allCards = cards.map { c -> c[i] }
                .sortedByDescending(Card::getPowerAsInt)
        }

        return listOf();
    }
}