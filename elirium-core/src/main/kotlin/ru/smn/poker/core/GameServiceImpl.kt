package ru.smn.poker.core

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import ru.smn.poker.game.StartGameRequest
import ru.smn.poker.game.StartGameResponse
import ru.smn.poker.log.EliriumLogger
import java.util.*

@Service
class GameServiceImpl(
    val gameStorage: GameStorage
) : GameService {
    private val runGames: MutableMap<UUID, Job> = mutableMapOf()
    override fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse {
        val gameId = UUID.randomUUID()

        with(gameStorage) {
            add(GameCore(gameId = gameId, instances = mutableListOf()))
        }

        EliriumLogger(
            message = "game created. id: $gameId"
        ).print()

        return CreateGameResponse(true, createGameRequest.countOfPlayer, gameId)
    }

    override fun startGame(startGameRequest: StartGameRequest): StartGameResponse {
        val gameId = startGameRequest.gameId
        val job = GlobalScope.launch {
            gameStorage.games
                .first { game -> game.gameId == gameId }
                .start()
        }
        this.runGames[gameId] = job
        return StartGameResponse(gameId)
    }
}