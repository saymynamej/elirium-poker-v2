package ru.smn.poker.core

import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import java.util.UUID


interface GameService {
    fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse
    fun startGame(gameId: UUID)
}