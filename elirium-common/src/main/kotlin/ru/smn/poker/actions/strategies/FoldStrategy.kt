package ru.smn.poker.actions.strategies

import org.springframework.stereotype.Component
import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance

@Component
class FoldStrategy : ActionStrategy {
    override val type = ActionType.FOLD
    override fun invoke(instance: Instance, deal: Deal) {
        return
    }
}