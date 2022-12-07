package ru.smn.poker.core

import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import ru.smn.poker.game.StartGameRequest
import ru.smn.poker.game.StartGameResponse


interface GameService {
    fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse
    fun startGame(startGameRequest: StartGameRequest): StartGameResponse
}