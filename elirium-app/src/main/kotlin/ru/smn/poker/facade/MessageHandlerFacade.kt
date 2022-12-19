package ru.smn.poker.facade

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.core.ActionService
import ru.smn.poker.core.GameService
import ru.smn.poker.core.SecretService
import ru.smn.poker.game.StartGameRequest

@Service
class MessageHandlerFacade(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val actionService: ActionService,
    private val secretService: SecretService,
    private val gameService: GameService,
) {
    fun doAction(actionRequest: ActionRequest) {
        val actionResponse = actionService.doAction(actionRequest)
        val instances = actionResponse.instances

        instances.forEach { instance ->
            simpMessagingTemplate.convertAndSendToUser(
                instance.name,
                "/output/poker/actions",
                secretService.secretActionResponse(instance.name, actionResponse)
            )
        }
    }

    fun startGame(instanceName: String, startGameRequest: StartGameRequest) {
        val startGameResponse = gameService.startGame(startGameRequest)
        val instances = startGameResponse.instances

        instances.forEach { instance ->
            simpMessagingTemplate.convertAndSendToUser(
                instance.name,
                "/output/poker/games",
                secretService.secretStartGameResponse(instance.name, startGameResponse)
            )
        }
    }
}