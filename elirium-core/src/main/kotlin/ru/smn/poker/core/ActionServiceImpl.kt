package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.*

@Service
class ActionServiceImpl(private val gameStorage: GameStorage) : ActionService {
    override fun doAction(actionRequest: ActionRequest): ActionResponse {
        val game = gameStorage.getById(actionRequest.gameId)

        game.lastAction(actionRequest)

        return ActionResponse(
            actionRequest.count,
            game.gameId,
            actionRequest.instanceName
        )
    }
}