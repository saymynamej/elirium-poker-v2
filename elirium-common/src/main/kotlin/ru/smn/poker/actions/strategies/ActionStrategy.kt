package ru.smn.poker.actions.strategies

import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance

interface ActionStrategy {

    val type: ActionType
    fun invoke(instance: Instance, deal: Deal)
}