package ru.smn.poker.actions

import java.util.UUID

data class ActionRequest(
    val count: Long,
    val gameId: UUID,
    val instanceName: String,
    val actionType: ActionType
)