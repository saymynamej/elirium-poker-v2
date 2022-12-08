package ru.smn.poker.actions

import java.util.UUID

data class ActionResponse(
    val count: Long,
    val gameId: UUID,
    val playerName: String,
)