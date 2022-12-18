package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.CountAction
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.exception.ForbiddenActionException

@Component
class CallStrategy : ActionStrategy {

    override val type = ActionType.CALL
    override fun invoke(instance: Instance, deal: Deal) {
        val action = instance.action as CountAction
        if (deal.lastBet == action.count) {
            return
        }
        throw ForbiddenActionException(action.type.name)

    }
}