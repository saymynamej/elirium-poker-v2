package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionResponse
import java.util.*

@Service
class ActionServiceImpl(
    private val gameStorage: GameStorage,
) : ActionService {

    override fun doAction(
        gameId: UUID,
        instanceName: String,
        action: Action,
    ): Game {
        val game = gameStorage.getById(gameId)

        game.instances
            .first { instance -> instance.name == instanceName }
            .action = action

        return game
    }

    override fun doAction(actionRequest: ActionRequest): ActionResponse {
        val gameId = actionRequest.gameId
        val game = doAction(
            gameId,
            actionRequest.instanceName,
            actionRequest.actionType.toAction()
        )
        return ActionResponse(
            actionRequest.count,
            gameId,
            actionRequest.instanceName,
            game.deal,
            game.instances
        )
    }
}