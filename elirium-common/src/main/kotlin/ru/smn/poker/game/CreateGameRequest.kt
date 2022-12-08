package ru.smn.poker.game

import java.util.UUID

data class CreateGameRequest(
    val gameType: GameType,
    val countOfPlayer: Byte,
    val gameId: UUID? = null,
)