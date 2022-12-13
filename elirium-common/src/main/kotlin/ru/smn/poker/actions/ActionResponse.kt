package ru.smn.poker.actions

import java.util.*

data class ActionResponse(
    val count: Long,
    val gameId: UUID,
    val playerName: String,
)