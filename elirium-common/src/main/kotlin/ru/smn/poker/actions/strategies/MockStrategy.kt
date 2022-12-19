package ru.smn.poker.actions.strategies

import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance

class MockStrategy : ActionStrategy {
    override val type = ActionType.NO_ACTION
    override fun invoke(instance: Instance, deal: Deal) {
        print("mock")
    }
}