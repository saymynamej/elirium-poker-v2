package ru.smn.poker.game

import java.util.*

data class CreateGameResponse(
    val success: Boolean,
    val countOfPlayers: Byte,
    val gameId: UUID
)