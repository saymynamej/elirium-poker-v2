package ru.smn.poker.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import ru.smn.poker.combination.CombinationService
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import ru.smn.poker.game.StartGameRequest
import ru.smn.poker.game.StartGameResponse
import ru.smn.poker.log.printC
import java.util.*

@Service
class GameServiceImpl(
    private val gameStorage: GameStorage,
    private val gameSetup: GameSetup,
    private val actionHandler: ActionHandler,
    private val combinationService: CombinationService
) : GameService {
    override fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse {
        val gameId = createGameRequest.gameId ?: UUID.randomUUID()

        with(gameStorage) {
            add(
                GameImpl(
                    gameId = gameId,
                    instances = mutableListOf(),
                    actionHandler = actionHandler,
                    gameSetup = gameSetup,
                    dealHandler = DealHandlerImpl(combinationService)
                )
            )
        }

        printC("game created. id: $gameId")

        return CreateGameResponse(true, createGameRequest.countOfPlayer, gameId)
    }

    override fun startGame(startGameRequest: StartGameRequest): StartGameResponse {
        val gameId = startGameRequest.gameId
        val game = gameStorage.getById(gameId)

        val job = CoroutineScope(Dispatchers.Default).launch {
            game.start()
        }

        gameStorage.addJob(game, job)

        return StartGameResponse(gameId)
    }
}