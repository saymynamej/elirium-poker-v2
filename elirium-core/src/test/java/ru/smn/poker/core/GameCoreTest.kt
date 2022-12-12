package ru.smn.poker.core

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.GameType
import ru.smn.poker.game.StartGameRequest
import java.util.*
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


@SpringBootTest(classes = [GameServiceImpl::class, GameStorage::class])
@ExtendWith(SpringExtension::class)
class GameCoreTest {

    @Autowired
    private lateinit var gameService: GameService

    private val gameID = UUID.randomUUID()
    @Test
    fun `should create and start game`() {
        val createGameResponse = gameService.createGame(
            CreateGameRequest(
                GameType.HOLDEM,
                6,
                gameID
            )
        )
        assertTrue(createGameResponse.success)
        assertNotNull(createGameResponse.gameId)
        val startGameResponse = gameService.startGame(StartGameRequest(gameId = gameID))
        assertNotNull(startGameResponse.gameId)
    }
}
