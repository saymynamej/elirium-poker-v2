package ru.smn.poker.core

import org.awaitility.Awaitility
import org.springframework.stereotype.Service
import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.Instance
import java.util.concurrent.TimeUnit

@Service
class ActionWaiterImpl : ActionWaiter {
    override fun wait(instance: Instance) {
        instance.active = true
        Awaitility.await()
            .atMost(instance.timeBank.toLong(), TimeUnit.SECONDS)
            .until { instance.action.type != ActionType.NO_ACTION }
    }
}