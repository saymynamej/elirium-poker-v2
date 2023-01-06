package ru.smn.poker.combination

import ru.smn.poker.combination.data.Combination
import ru.smn.poker.combination.data.CombinationType

interface CombinationComparer {
    fun compare(combinationType: CombinationType, combinations: List<Combination>): List<Combination>
}