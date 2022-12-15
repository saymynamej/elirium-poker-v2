package ru.smn.poker.dto

import ru.smn.poker.actions.Action
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.NoAction
import ru.smn.poker.actions.Role
import ru.smn.poker.combination.data.Card

data class Instance(
    val name: String,
    var active: Boolean = false,
    var action: Action = NoAction(),
    var timeBank: Int = 120,
    var role: Role = Role.EMPTY,
    var history: Map<Stage, MutableList<Action>> = Stage.values()
        .associateBy({ it }, { mutableListOf() }),
    var cards: MutableList<Card> = mutableListOf(),
    var chips: Long = 0,
) {

    fun lastActionIs(stage: Stage, actionType: ActionType): Boolean {
        return actionType == history[stage]!!.lastOrNull()?.type
    }

    fun lastActionIs(actionType: ActionType): Boolean {
        val lastAction = history.toSortedMap { o1, o2 -> o1.grade - o2.grade }
            .values
            .lastOrNull { actions -> actions.isNotEmpty() }
            ?.lastOrNull()
        return actionType == lastAction?.type
    }
}