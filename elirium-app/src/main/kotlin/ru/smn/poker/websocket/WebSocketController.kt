package ru.smn.poker.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionResponse
import ru.smn.poker.core.GameService
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse

@RestController
class WebSocketController(private val gameService: GameService) {

    @SendTo("/poker/games")
    @MessageMapping("/createGame")
    fun createGame(@Payload createGame: CreateGameRequest): CreateGameResponse {
        return gameService.createGame(createGame)
    }

    @SendTo("/poker/actions")
    @MessageMapping("/doAction")
    fun doAction(@Payload actionRequest: ActionRequest): ActionResponse {
        return ActionResponse(1000, "hello")
    }
}