package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.exception.ForbiddenActionException

@Component
class BetStrategy : ActionStrategy {

    override val type = ActionType.BET
    override fun invoke(instance: Instance, deal: Deal) {
        if (deal.stage.type.isPostFlop()) {
            if (deal.lastBet == 0L) {
                return
            }
        }
        throw ForbiddenActionException(instance.action.type.name)
    }
}