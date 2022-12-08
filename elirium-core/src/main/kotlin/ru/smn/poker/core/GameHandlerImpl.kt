package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.dto.Data
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.EliriumLogger

class GameHandlerImpl : GameHandler {
    override fun handle(data: Data, action: Action, instance: Instance) {
        data.bank += action.count()
        instance.chips -= action.count()
        instance.history[data.stage]!!.add(action)
        EliriumLogger("handle action:$action, by instance:$instance, data:$data").print()
    }
}