package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionResponse
import java.util.*

interface ActionService {
    fun doAction(actionRequest: ActionRequest): ActionResponse
    fun doAction(gameId: UUID, instanceName: String, action: Action): Game
}