package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.actions.BetAction
import ru.smn.poker.actions.Role
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.log.EliriumLogger

class GameHandlerImpl : GameHandler {

    override fun handle(deal: Deal, action: Action, instance: Instance) {
        deal.bank += action.count()
        instance.chips -= action.count()
        instance.history[deal.stage.type]!!.add(action)
        EliriumLogger("handle action:$action, by instance:$instance, data:$deal").print()
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