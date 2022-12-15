package ru.smn.poker.facade

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.core.ActionService
import ru.smn.poker.core.SecretService

@Service
class MessagingFacade(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val actionService: ActionService,
    private val secretService: SecretService
) {
    fun doAction(actionRequest: ActionRequest) {
        val actionResponse = actionService.doAction(actionRequest)
        val instances = actionResponse.instances

        instances.forEach { instance ->
            simpMessagingTemplate.convertAndSendToUser(
                instance.name,
                "/poker/games",
                secretService.secretActionResponse(instance.name, actionResponse)
            )
        }
    }
}