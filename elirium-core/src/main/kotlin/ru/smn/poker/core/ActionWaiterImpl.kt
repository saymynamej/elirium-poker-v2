package ru.smn.poker.core

import org.awaitility.Awaitility
import org.awaitility.core.ConditionTimeoutException
import org.springframework.stereotype.Service
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.FoldAction
import ru.smn.poker.dto.Instance
import java.util.concurrent.TimeUnit

@Service
class ActionWaiterImpl : ActionWaiter {
    override fun wait(instance: Instance): Long {
        instance.active = true
        val startTime = System.currentTimeMillis()

        try {
            Awaitility.await()
                .atMost(20 + instance.timeBank.toLong(), TimeUnit.SECONDS)
                .until { instance.action.type != ActionType.NO_ACTION }
        } catch (e: ConditionTimeoutException) {
            println(e.message)
            instance.timeBank = 0
            instance.action = FoldAction()
        }

        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }
}
