package ru.smn.poker.core

import org.awaitility.Awaitility.await
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.MockAction
import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.EliriumLogger
import java.util.*


class GameCore(
    private var lastAction: Action = MockAction(),
    val gameId: UUID,
    val instances: MutableList<Instance>
) {
    fun start() {
        EliriumLogger("game started. id = $gameId").print()
        this.instances
            .shuffled()
            .distributeRoles()
            .forEach { instance ->
                instance.active = true
                await().until { lastAction.actionType() != ActionType.MOCK }
                instance.active = false
            }
    }

    fun lastAction(actionRequest: ActionRequest) {
        val activeInstance = instances.first { instance -> instance.active }
        if (activeInstance.instanceName != actionRequest.instanceName)
            throw RuntimeException("activeInstance has other name")
        val action = actionRequest.actionType.toAction(actionRequest.count)
        this.lastAction = action
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }
}
