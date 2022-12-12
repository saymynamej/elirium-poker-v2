package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.*
import java.util.*

@Service
class ActionServiceImpl(private val gameStorage: GameStorage) : ActionService {


    override fun doAction(
        gameId: UUID,
        instanceName: String,
        action: Action,
    ) {
        val game = gameStorage.getById(gameId)

        game.instances
            .first { instance -> instance.instanceName == instanceName }
            .action = action
    }

    override fun doAction(actionRequest: ActionRequest): ActionResponse {
        val gameId = actionRequest.gameId

        doAction(
            gameId,
            actionRequest.instanceName,
            actionRequest.actionType.toAction()
        )

        return ActionResponse(
            actionRequest.count,
            gameId,
            actionRequest.instanceName
        )
    }
}