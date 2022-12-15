package ru.smn.poker.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.core.GameService
import ru.smn.poker.facade.MessagingFacade
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.StartGameRequest
import ru.smn.poker.game.StartGameResponse

@RestController
class WebSocketController(
    private val gameService: GameService,
    private val messagingFacade: MessagingFacade,
) {

    @SendTo("/poker/games")
    @MessageMapping("/game/create")
    fun createGame(@Payload createGame: CreateGameRequest) = gameService.createGame(createGame)

    @MessageMapping("/game/action")
    fun doAction(@Payload actionRequest: ActionRequest) = messagingFacade.doAction(actionRequest)

    @SendTo("/poker/games")
    @MessageMapping("/game/start")
    fun startGame(
        simpMessageHeaderAccessor: SimpMessageHeaderAccessor,
        @Payload startGameRequest: StartGameRequest,
    ): StartGameResponse {
        return gameService.startGame(startGameRequest)
    }

}