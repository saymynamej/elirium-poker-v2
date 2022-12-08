package ru.smn.poker.mock

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import ru.smn.poker.core.GameService
import ru.smn.poker.core.InstanceService
import ru.smn.poker.dto.Instance
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.GameType
import ru.smn.poker.game.StartGameRequest
import java.util.UUID

@Service
class AddMockInstances(
    private val gameService: GameService,
    private val instanceService: InstanceService,
) {
    @PostConstruct
    fun createMockGameAndInstances() {
        val gameId = UUID.fromString("0040073d-2634-4f1d-8bc9-e1aa2ab20740")
        val createGameResponse = gameService.createGame(
            CreateGameRequest(GameType.HOLDEM, 9, gameId)
        )
        instanceService.addInstance(gameId, Instance("1", chips = 5000))
        instanceService.addInstance(gameId, Instance("2", chips = 5000))
        instanceService.addInstance(gameId, Instance("3", chips = 5000))
        gameService.startGame(StartGameRequest(gameId))
    }
}