package ru.smn.poker.core

import ru.smn.poker.dto.*
import java.util.*


class Game(
    private val gameSetup: GameSetup,
    private val actionHandler: ActionHandler,
    private val dealHandler: DealHandler,
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
                print("stage: $stage is started")
                val nextInstanceFunction = instances.next()
                while (instances.isStageNotFinished(stage)) {
                    actionHandler.waitAndHandle(deal, nextInstanceFunction())
                }
                dealHandler.handleDeal(deal)
                if (instances.isDealFinished(stage)) {
                    deal.finished = true
                    print("deal is finished: $deal")
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
