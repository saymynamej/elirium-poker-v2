package ru.smn.poker.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.core.GameService
import ru.smn.poker.facade.MessageHandlerFacade
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.JoinGameRequest
import ru.smn.poker.game.StartGameRequest

@RestController
class WebSocketController(
    private val gameService: GameService,
    private val messageHandlerFacade: MessageHandlerFacade,
) {

    @SendTo("/output/poker/games")
    @MessageMapping("/input/game/join")
    fun joinGame(@Payload joinGameRequest: JoinGameRequest) = gameService.joinGame(joinGameRequest)

    @SendTo("/output/poker/games")
    @MessageMapping("/input/game/create")
    fun createGame(@Payload createGameRequest: CreateGameRequest) = gameService.createGame(createGameRequest)

    @MessageMapping("/input/game/start")
    fun startGame(simpMessageHeaderAccessor: SimpMessageHeaderAccessor, @Payload startGameRequest: StartGameRequest) {
        messageHandlerFacade.startGame(
            simpMessageHeaderAccessor.user?.name ?: throw RuntimeException("cannot retrieve user name"),
            startGameRequest
        )
    }

    @MessageMapping("/input/game/action")
    fun doAction(@Payload actionRequest: ActionRequest) = messageHandlerFacade.doAction(actionRequest)

}