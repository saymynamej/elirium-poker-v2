package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.CountAction
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.exception.ForbiddenActionException

@Component
class RaiseStrategy : ActionStrategy {
    override val type = ActionType.RAISE
    override fun invoke(instance: Instance, deal: Deal) {
        val action = instance.action as CountAction
        if (deal.lastBet != 0L) {
            if (action.count >= deal.lastBet * 2) {
                return
            }
        }

        throw ForbiddenActionException(instance.action.type.name)
    }
}