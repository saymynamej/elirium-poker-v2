package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import ru.smn.poker.log.EliriumLogger
import java.util.*

@Service
class GameServiceImpl(val gameStorage: GameStorage) : GameService {

    override fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse {
        val gameId = UUID.randomUUID()
        with(gameStorage) {
            add(GameCore(gameId, mutableListOf()))
        }

        EliriumLogger(
            message = "game created. id: $gameId"
        ).print()
        return CreateGameResponse(true, createGameRequest.countOfPlayer)
    }

    override fun startGame(gameId: UUID) {
        gameStorage.games
            .first { game -> game.gameId == gameId }
            .start()
    }
}