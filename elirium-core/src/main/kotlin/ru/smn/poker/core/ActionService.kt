package ru.smn.poker.core

import ru.smn.poker.actions.ActionRequest
import ru.smn.poker.actions.ActionResponse

interface ActionService {
    fun doAction(actionRequest: ActionRequest): ActionResponse
}