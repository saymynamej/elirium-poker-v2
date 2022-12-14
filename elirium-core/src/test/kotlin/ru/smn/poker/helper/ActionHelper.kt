package ru.smn.poker.helper

import org.awaitility.Awaitility
import org.springframework.stereotype.Service
import ru.smn.poker.actions.Action
import ru.smn.poker.core.ActionService
import ru.smn.poker.dto.Instance
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.test.assertTrue

@Service
class ActionHelper(private val actionService: ActionService) {

    fun waitActiveAndDoAction(instance: Instance, gameId: UUID, action: Action) {
        waitActive(instance)
        assertTrue(instance.active)
        doAction(gameId, instance, action)
    }

    fun doAction(gameId: UUID, instance: Instance, action: Action) {
        actionService.doAction(
            gameId,
            instance.instanceName,
            action
        )
    }

    fun waitUntil(predicate: () -> Boolean) {
        Awaitility.await()
            .atMost(3, TimeUnit.SECONDS)
            .until { predicate.invoke() }
    }

    fun waitActive(instance: Instance) {
        Awaitility.await()
            .atMost(3, TimeUnit.SECONDS)
            .until { instance.active }
    }
}