package ru.smn.poker.core

import ru.smn.poker.dto.*
import ru.smn.poker.log.printC
import java.util.*


class GameImpl(
    private val dealCustomizer: DealCustomizer,
    private val actionHandler: ActionHandler,
    private val dealHandler: DealHandler,
    private val actionWaiter: ActionWaiter,
    override val gameId: UUID,
    override val instances: MutableList<Instance>,
    override var active: Boolean = false,
    override val deal: Deal = Deal(gameId),
) : Game {
    override suspend fun start() {
        this.active = true
        while (canStart()) {
            var instances = instances.distributeRoles().toMutableList()
            dealCustomizer.customize(gameId, deal, instances)
            while (!deal.finished) {
                val stage = deal.stage.type
                instances = instances.sortByStage(stage)
                printC("stage: $stage is started")
                val nextInstanceFunction = instances.nextInstanceFunction()
                while (instances.isStageNotFinished(stage)) {
                    val instance = nextInstanceFunction()
                    actionWaiter.wait(instance)
                    actionHandler.handle(deal, instance)
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

    private fun canStart(): Boolean {
        return this.active && this.instances.size > 2
    }
}
