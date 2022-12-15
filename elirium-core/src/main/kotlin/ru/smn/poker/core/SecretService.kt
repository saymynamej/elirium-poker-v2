package ru.smn.poker.core

import ru.smn.poker.actions.ActionResponse
import ru.smn.poker.game.StartGameResponse

interface SecretService {
    fun secretActionResponse(targetInstanceName: String, actionResponse: ActionResponse): ActionResponse
    fun secretStartGameResponse(targetInstanceName: String, startGameResponse: StartGameResponse): StartGameResponse
}