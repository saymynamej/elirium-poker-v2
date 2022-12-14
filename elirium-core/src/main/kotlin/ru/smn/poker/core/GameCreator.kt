package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.dto.Instance
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.GameType
import ru.smn.poker.game.StartGameRequest
import java.util.*

@Service
class GameCreator(
    val gameService: GameService,
    val instanceService: InstanceService,
) {
    fun createDefaultGame(countOfPlayer: Int): UUID {
        val gameId = UUID.randomUUID()

        gameService.createGame(
            CreateGameRequest(
                GameType.HOLDEM,
                6,
                gameId
            )
        )

        val listOfInstances = (1..countOfPlayer).map {
            Instance("test-0$it")
        }.toList()

        with(instanceService) {
            addInstances(
                gameId,
                listOfInstances
            )
        }

        return gameService.startGame(StartGameRequest(gameId)).gameId
    }
}