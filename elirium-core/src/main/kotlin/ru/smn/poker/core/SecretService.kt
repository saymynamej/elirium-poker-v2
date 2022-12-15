package ru.smn.poker.core

import ru.smn.poker.actions.ActionResponse

interface SecretService {
    fun secretActionResponse(targetInstanceName: String, actionResponse: ActionResponse): ActionResponse
}