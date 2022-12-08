package ru.smn.poker.core

import org.awaitility.Awaitility
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.MockAction
import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.everyOneHasTheSameBet
import ru.smn.poker.log.EliriumLogger
import java.util.*
import java.util.concurrent.TimeUnit


class GameCore(
    private val mockAction: MockAction = MockAction(),
    private var lastAction: Action = mockAction,
    private val active: Boolean = true,
    private val gameHandler: GameHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>,
) {
    fun start() {
        EliriumLogger("game started. id = $gameId").print()
        while (active) { // САМА ИГРА
            val deal = Deal(gameId)
            val instances = this.instances.distributeRoles()
            gameHandler.handleBlinds(deal, this.instances)
            while (deal.run) {
                when (deal.stage.type) {
                    Stage.NONE -> deal.stage.type = Stage.PRE_FLOP
                    Stage.PRE_FLOP -> deal.stage.type = Stage.FLOP
                    Stage.FLOP -> deal.stage.type = Stage.TERN
                    Stage.TERN -> deal.stage.type = Stage.RIVER
                    Stage.RIVER -> deal.stage.type = Stage.RIVER
                }

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
                            .until { lastAction.actionType() != ActionType.MOCK }
                        gameHandler.handle(deal, this.lastAction, instance)
                        this.lastAction = mockAction
                        instance.active = false
                    }
                }
            }
        }
        EliriumLogger("game stopped. id = $gameId").print()
    }

    fun lastAction(actionRequest: ActionRequest) {
        val activeInstance = instances.first { instance -> instance.active }
        if (activeInstance.instanceName != actionRequest.instanceName)
            throw RuntimeException("active instance has other name")
        val action = actionRequest.actionType.toAction(actionRequest.count)
        this.lastAction = action
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }
}
