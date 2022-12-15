package ru.smn.poker.core

import ru.smn.poker.dto.*
import ru.smn.poker.log.printC
import java.util.*


class GameImpl(
    private val dealCustomizer: DealCustomizer,
    private val actionHandler: ActionHandler,
    private val dealHandler: DealHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>,
    val deal: Deal = Deal(gameId),
    var active: Boolean = false,
) : Game {
    override suspend fun start() {
        this.active = true
        while (active) {
            var instances = instances.distributeRoles().toMutableList()
            dealCustomizer.customize(gameId, deal, instances)
            while (!deal.finished) {
                val stage = deal.stage.type
                instances = instances.setUp(stage)
                printC("stage: $stage is started")
                val nextInstanceFunction = instances.nextInstanceFunction()
                while (instances.isStageNotFinished(stage)) {
                    actionHandler.waitAndHandle(deal, nextInstanceFunction())
                }
                if (instances.isDealFinished(stage)) {
                    deal.finished = true
                    dealHandler.handle(instances, deal)
                    printC("deal is finished: $deal")
                    return
                }
                deal.nextStage()
            }
        }
    }

    override suspend fun stop() {
        this.active = false
    }

    override fun addInstance(instance: Instance) {
        instances.add(instance)
    }

    override fun removeInstance(instance: Instance) {
        instances.remove(instance)
    }
}
