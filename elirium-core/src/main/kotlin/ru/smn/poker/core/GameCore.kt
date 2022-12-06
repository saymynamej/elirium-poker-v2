package ru.smn.poker.core

import org.awaitility.Awaitility.await
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.MockAction
import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.EliriumLogger
import java.util.*


class GameCore(
    val gameId: UUID,
    val instances: MutableList<Instance>,
    var lastAction: Action = MockAction()
) {
    fun start() {
        EliriumLogger(
            "game started. id = $gameId"
        ).print()

        this.instances.shuffled().distributeRoles().forEach { instance ->
            await().until { lastAction.actionType() != ActionType.MOCK }
        }
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }

}