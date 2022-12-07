package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.*

@Service
class ActionServiceImpl(private val gameStorage: GameStorage) : ActionService {
    override fun doAction(actionRequest: ActionRequest): ActionResponse {
        val game = gameStorage.games
            .first { game -> game.gameId == actionRequest.gameId }

        game.lastAction = actionRequest.actionType.toAction(actionRequest.count)

        return ActionResponse(actionRequest.count, actionRequest.playerName)
    }
}