package ru.smn.poker.core

import ru.smn.poker.game.*


interface GameService {
    fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse
    fun startGame(startGameRequest: StartGameRequest): StartGameResponse
    fun joinGame(joinGameRequest: JoinGameRequest): JoinGameResponse
}