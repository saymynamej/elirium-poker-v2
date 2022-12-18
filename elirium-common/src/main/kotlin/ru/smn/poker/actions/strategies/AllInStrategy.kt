package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.exception.ForbiddenActionException


@Component
class AllInStrategy : ActionStrategy {

    override val type = ActionType.ALL_IN

    override fun invoke(instance: Instance, deal: Deal) {
        if (instance.chips != 0L) {
            return
        }
        throw ForbiddenActionException(instance.action.type.name)
    }
}