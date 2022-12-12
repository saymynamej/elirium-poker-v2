package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.*

@Service
class ActionServiceImpl(private val gameStorage: GameStorage) : ActionService {
    override fun doAction(actionRequest: ActionRequest): ActionResponse {
        val game = gameStorage.getById(actionRequest.gameId)

        game.instances
            .first { instance -> instance.instanceName == actionRequest.instanceName }
            .action = actionRequest.actionType.toAction(actionRequest.count)

        return ActionResponse(
            actionRequest.count,
            game.gameId,
            actionRequest.instanceName
        )
    }
}