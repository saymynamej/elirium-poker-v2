package ru.smn.poker.core

import org.awaitility.Awaitility
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.NoAction
import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.everyOneHasTheSameBet
import ru.smn.poker.log.EliriumLogger
import java.util.*
import java.util.concurrent.TimeUnit


class GameCore(
    private val active: Boolean = true,
    private val gameHandler: GameHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>,
) {
    fun start() {
        EliriumLogger("game started. id = $gameId").print()
        while (active) {
            val deal = Deal(gameId)
            val instances = this.instances.distributeRoles()
            gameHandler.handleBlinds(deal, this.instances)
            while (deal.run) {
                deal.stage.type = deal.stage.nextStage()
                EliriumLogger("stage is:${deal.stage.type}").print()
                while (true) {
                    if (instances.everyOneHasTheSameBet(deal.stage.type)) {
                        break
                    }
                    for (instance in instances) {
                        instance.active = true
                        EliriumLogger("active player: $instance").print()

                        Awaitility.await()
                            .atMost(instance.timeBank.toLong(), TimeUnit.SECONDS)
                            .until { instance.action.actionType != ActionType.MOCK }

                        instance.apply {
                            gameHandler.handle(deal, action, this)
                            action = NoAction()
                            active = false
                        }
                    }
                }
            }
        }
        EliriumLogger("game stopped. id = $gameId").print()
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }
}
