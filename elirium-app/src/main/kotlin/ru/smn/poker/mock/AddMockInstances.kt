package ru.smn.poker.mock

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import ru.smn.poker.core.GameService
import ru.smn.poker.core.InstanceService
import ru.smn.poker.dto.Instance
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.GameType
import ru.smn.poker.game.StartGameRequest

@Service
class AddMockInstances(
    private val gameService: GameService,
    private val instanceService: InstanceService,
) {
    @PostConstruct
    fun createMockGameAndInstances() {
        val createGameResponse = gameService.createGame(
            CreateGameRequest(GameType.HOLDEM, 9)
        )
        val gameId = createGameResponse.gameId
        instanceService.addInstance(gameId, Instance("1", chips = 5000))
        instanceService.addInstance(gameId, Instance("2", chips = 5000))
        instanceService.addInstance(gameId, Instance("3", chips = 5000))
        gameService.startGame(StartGameRequest(gameId))
    }
}