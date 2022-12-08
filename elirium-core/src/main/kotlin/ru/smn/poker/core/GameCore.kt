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
import java.util.concurrent.TimeUnit


class GameCore(
    private val mockAction: MockAction = MockAction(),
    private var lastAction: Action = mockAction,
    val gameHandler: GameHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>
) {
    fun start() {
        EliriumLogger("game started. id = $gameId").print()
            //Установить роли если их еще нету
           //Далее крутить баттон сб и бб
        this.instances
            .distributeRoles()
            .forEach { instance ->
                instance.active = true
                await()
                    .atMost(instance.timeBank.toLong(), TimeUnit.SECONDS)
                    .until { lastAction.actionType() != ActionType.MOCK }
                gameHandler.handle(this.lastAction, instance)
                instance.active = false
                this.lastAction = mockAction
            }
    }

    fun lastAction(actionRequest: ActionRequest) {
        val activeInstance = instances.first { instance -> instance.active }
        if (activeInstance.instanceName != actionRequest.instanceName)
            throw RuntimeException("active instance has other name")
        val action = actionRequest.actionType.toAction(actionRequest.count)
        this.lastAction = action
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }
}
