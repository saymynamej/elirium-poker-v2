package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.exception.ForbiddenActionException

@Component
class CheckStrategy : ActionStrategy {

    override val type = ActionType.CHECK
    override fun invoke(instance: Instance, deal: Deal) {
        val stage = deal.stage.type
        if (stage.isPreFlop()) {
            if (instance.isBigBlind()) {
                if (deal.lastBet == deal.bigBlindBet) {
                    return
                }
            }
        }

        if (stage.isPostFlop()) {
            if (deal.lastBet == 0L) {
                return
            }
        }

        throw ForbiddenActionException(instance.action.type.name)
    }

}