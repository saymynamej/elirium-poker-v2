package ru.smn.poker.combination.strategy

import ru.smn.poker.combination.data.Combination

interface Comparer {
    fun compare(combinations: List<Combination>): List<Combination>
}