package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.actions.BetAction
import ru.smn.poker.actions.Role
import ru.smn.poker.dto.Data
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.log.EliriumLogger

class GameHandlerImpl : GameHandler {

    override fun handle(data: Data, action: Action, instance: Instance) {
        data.bank += action.count()
        instance.chips -= action.count()
        instance.history[data.stage]!!.add(action)
        EliriumLogger("handle action:$action, by instance:$instance, data:$data").print()
    }

    override fun handleBlinds(data: Data, instances: MutableList<Instance>) {
        val bigBlind = instances.first { instance ->
            instance.role == Role.BIG_BLIND
        }
        val smallBlind = instances.first { instance ->
            instance.role == Role.SMALL_BLIND
        }
        bigBlind.history[Stage.PRE_FLOP]!!.add(BetAction(data.bigBlindBet))
        smallBlind.history[Stage.PRE_FLOP]!!.add(BetAction(data.smallBlindBet))
    }
}