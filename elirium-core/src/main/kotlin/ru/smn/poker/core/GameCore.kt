package ru.smn.poker.core

import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.isStageNotFinished
import ru.smn.poker.log.EliriumLogger
import ru.smn.poker.setUp
import java.util.*


class GameCore(
    private val gameSetup: GameSetup,
    private val gameHandler: GameHandler,
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
                EliriumLogger("stage: $stage is started").print()
                while (instances.isStageNotFinished(stage)) {
                    instances.forEach { instance ->
                        gameHandler.waitAndHandle(deal, instance)
                    }
                }
                if (stage == Stage.RIVER) {
                    deal.finished = true
                    return
                }
                deal.stage = deal.nextStage()
            }
        }
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }

}
