package ru.smn.poker.game

import java.util.UUID

data class CreateGameResponse(
    val success: Boolean,
    val countOfPlayers: Byte,
    val gameId: UUID
)