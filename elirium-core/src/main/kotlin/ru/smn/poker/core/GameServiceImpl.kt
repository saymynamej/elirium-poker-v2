package ru.smn.poker.core

import kotlinx.coroutines.GlobalScope
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
    val gameStorage: GameStorage,
) : GameService {
    override fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse {
        val gameId = createGameRequest.gameId ?: UUID.randomUUID()

        with(gameStorage) {
            add(
                GameCore(
                    gameId = gameId,
                    instances = mutableListOf(),
                    gameHandler = GameHandlerImpl()
                )
            )
        }

        EliriumLogger(message = "game created. id: $gameId").print()

        return CreateGameResponse(true, createGameRequest.countOfPlayer, gameId)
    }

    override fun startGame(startGameRequest: StartGameRequest): StartGameResponse {
        val gameId = startGameRequest.gameId
        val game = gameStorage.getById(gameId)

        val job = GlobalScope.launch {
            game.start()
        }

        gameStorage.addJob(game, job)

        return StartGameResponse(gameId)
    }
}