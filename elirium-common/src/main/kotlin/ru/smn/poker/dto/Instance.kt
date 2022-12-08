package ru.smn.poker.dto

import ru.smn.combination.data.Card
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.Role

data class Instance(
    val instanceName: String,
    var active: Boolean = false,
    var timeBank: Int = 120,
    var role: Role = Role.EMPTY,
    var history: Map<Stage, MutableList<Action>> = Stage.values().associateBy({ it }, { mutableListOf() }),
    var cards: List<Card> = listOf(),
    var chips: Long = 0,
)