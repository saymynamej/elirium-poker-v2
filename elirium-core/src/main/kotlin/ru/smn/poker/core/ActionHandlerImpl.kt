package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.*
import ru.smn.poker.actions.strategies.ActionStrategy
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.log.printC

@Service
class ActionHandlerImpl(private val strategies: Map<ActionType, ActionStrategy>) : ActionHandler {

    override suspend fun handle(deal: Deal, instance: Instance) {
        printC("active player is: $instance")
        val action = instance.action
        strategies[action.type]!!.invoke(instance, deal)
        if (action is CountAction) {
            deal.bank += action.count
            instance.chips -= action.count
            deal.lastBet = action.count
        }
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
        deal.lastBet = deal.bigBlindBet
    }
}