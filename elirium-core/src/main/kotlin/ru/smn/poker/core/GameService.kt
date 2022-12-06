package ru.smn.poker.core

import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse


interface GameService {
    fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse
}