package ru.smn.poker.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import ru.smn.poker.dto.Instance
import ru.smn.poker.game.*
import ru.smn.poker.log.printC
import java.util.*

@Service
class GameServiceImpl(
    private val gameStorage: GameStorage,
    private val dealCustomizer: DealCustomizer,
    private val actionHandler: ActionHandler,
    private val dealHandler: DealHandler,
    private val actionWaiter: ActionWaiter,
    private val bankTimeManager: BankTimeManager
) : GameService {

    override fun joinGame(joinGameRequest: JoinGameRequest): JoinGameResponse {
        val game = gameStorage.getById(joinGameRequest.gameId)
        game.addInstance(Instance(joinGameRequest.instanceName))
        return JoinGameResponse(true)
    }

    override fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse {
        val gameId = createGameRequest.gameId ?: UUID.randomUUID()

        with(gameStorage) {
            add(
                GameImpl(
                    gameId = gameId,
                    instances = mutableListOf(),
                    actionHandler = actionHandler,
                    dealCustomizer = dealCustomizer,
                    dealHandler = dealHandler,
                    actionWaiter = actionWaiter,
                    bankTimeManager = bankTimeManager
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

        return StartGameResponse(gameId, game.deal, game.instances)
    }
}
