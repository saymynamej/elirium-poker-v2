package ru.smn.poker.core

import ru.smn.poker.actions.ActionType
import ru.smn.poker.dto.*
import ru.smn.poker.log.EliriumLogger
import java.util.*


class Game(
    private val gameSetup: GameSetup,
    private val actionHandler: ActionHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>,
    val deal: Deal = Deal(gameId),
    var active: Boolean = false,
) {
    suspend fun start() {
        this.active = true
        while (active) {
            var instances = instances.distributeRoles().toMutableList()
            gameSetup.setUp(gameId, deal, instances)
            while (!deal.finished) {
                val stage = deal.stage.type
                instances = instances.setUp(stage)
                EliriumLogger.print("stage: $stage is started")
                val nextInstanceFunction = instances.next()
                while (instances.isStageNotFinished(stage)) {
                    actionHandler.waitAndHandle(deal, nextInstanceFunction())
                }
                if (stage == Stage.RIVER) {
                    deal.finished = true
                    return
                }
                deal.nextStage()
            }
        }
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }

}
