package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType


@Component
class ActionFactory(private val strategies: Map<ActionType, ActionStrategy>) {

    fun getByActionType(actionType: ActionType) = strategies[actionType]!!
}