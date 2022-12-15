package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.BetAction
import ru.smn.poker.actions.NoAction
import ru.smn.poker.actions.Role
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.log.printC

@Service
class ActionHandlerImpl : ActionHandler {

    override suspend fun handle(deal: Deal, instance: Instance) {
        printC("active player is: $instance")
        val action = instance.action
        deal.bank += action.count()
        instance.chips -= action.count()
        instance.history[deal.stage.type]!!.add(action)
        printC("handle action: $action, by instance: $instance, data: $deal")
        instance.active = false
        instance.action = NoAction()
    }

    override fun handleBlinds(deal: Deal, instances: MutableList<Instance>) {
        val bigBlind = instances.first { instance ->
            instance.role == Role.BIG_BLIND
        }
        val smallBlind = instances.first { instance ->
            instance.role == Role.SMALL_BLIND
        }
        bigBlind.history[Stage.PRE_FLOP]!!.add(BetAction(deal.bigBlindBet))
        smallBlind.history[Stage.PRE_FLOP]!!.add(BetAction(deal.smallBlindBet))
        smallBlind.chips -= deal.smallBlindBet
        bigBlind.chips -= deal.bigBlindBet
    }
}