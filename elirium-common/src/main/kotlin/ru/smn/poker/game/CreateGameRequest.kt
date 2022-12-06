package ru.smn.poker.game

data class CreateGameRequest(
    val gameType: GameType,
    val countOfPlayer: Byte
)