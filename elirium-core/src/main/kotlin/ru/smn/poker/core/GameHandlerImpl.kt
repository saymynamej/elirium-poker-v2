package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.EliriumLogger

class GameHandlerImpl : GameHandler {
    override fun handle(action: Action, instance: Instance) {
        EliriumLogger("handle action:$action, by instance:$instance").print()
    }
}