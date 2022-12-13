package ru.smn.poker.dto

import ru.smn.poker.actions.Action
import ru.smn.poker.actions.NoAction
import ru.smn.poker.actions.Role
import ru.smn.poker.combination.data.Card

data class Instance(
    val instanceName: String,
    var active: Boolean = false,
    var action: Action = NoAction(),
    var timeBank: Int = 120,
    var role: Role = Role.EMPTY,
    var history: Map<Stage, MutableList<Action>> = Stage.values()
        .associateBy({ it }, { mutableListOf() }),
    var cards: MutableList<Card> = mutableListOf(),
    var chips: Long = 0,
)