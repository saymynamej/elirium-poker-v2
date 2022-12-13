package ru.smn.poker.core

import ru.smn.poker.dto.*
import ru.smn.poker.log.EliriumLogger
import java.util.*


class GameCore(
    private val gameSetup: GameSetup,
    private val gameHandler: GameHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>,
    val deal: Deal = Deal(gameId),
    var active: Boolean = false
) {
    suspend fun start() {
        this.active = true
        while (active) {
            var instances = instances.distributeRoles().toMutableList()
            gameSetup.setUp(gameId, deal, instances)
            while (!deal.finished) {
                val stage = deal.stage.type
                instances = instances.setUp(stage)
                EliriumLogger("stage: $stage is started").print()
                val getInstanceForAction = instances.next()
                while (instances.isStageNotFinished(stage)) {
                    gameHandler.waitAndHandle(deal, getInstanceForAction())
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
