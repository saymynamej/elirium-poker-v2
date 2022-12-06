package ru.smn.poker.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionResponse
import ru.smn.poker.actions.ActionService
import ru.smn.poker.core.GameService
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import java.util.UUID

@RestController
class WebSocketController(
    private val gameService: GameService,
    private val actionService: ActionService
) {
    @SendTo("/poker/games")
    @MessageMapping("/game/create")
    fun createGame(@Payload createGame: CreateGameRequest): CreateGameResponse {
        return gameService.createGame(createGame)
    }

    @SendTo("/poker/actions")
    @MessageMapping("/game/action")
    fun doAction(@Payload actionRequest: ActionRequest): ActionResponse {
        return actionService.doAction(actionRequest)
    }

    @SendTo("/poker/games")
    @MessageMapping("/startGame")
    fun createGame(@Payload gameId: UUID) {
        gameService.startGame(gameId)
    }
}