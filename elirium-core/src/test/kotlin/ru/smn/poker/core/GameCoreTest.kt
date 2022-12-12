package ru.smn.poker.core

import org.awaitility.Awaitility
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.smn.poker.actions.*
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.GameType
import ru.smn.poker.game.StartGameRequest
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest(
    classes = [
        GameServiceImpl::class,
        GameStorage::class,
        InstanceService::class,
        ActionServiceImpl::class,
        GameServiceImpl::class
    ]
)
@ExtendWith(SpringExtension::class)
class GameCoreTest {

    @Autowired
    private lateinit var gameService: GameService

    @Autowired
    private lateinit var instanceService: InstanceService

    @Autowired
    private lateinit var actionService: ActionService


    @Autowired
    private lateinit var gameStorage: GameStorage

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

    @Test
    fun `should create and start game and do actions`() {
        val createGameResponse = gameService.createGame(
            CreateGameRequest(
                GameType.HOLDEM,
                6,
                gameID
            )
        )
        val gameId = createGameResponse.gameId
        assertTrue(createGameResponse.success)
        assertNotNull(gameId)

        val buttonInstance = Instance("test-01")
        val smallBlindInstance = Instance("test-02")
        val bigBlindInstance = Instance("test-03")
        val firstInstance = Instance("test-04")

        with(instanceService) {
            addInstances(
                gameId,
                listOf(
                    buttonInstance,
                    smallBlindInstance,
                    bigBlindInstance,
                    firstInstance
                )
            )
        }

        gameService.startGame(StartGameRequest(gameId = gameID))
        val game = gameStorage.getById(gameId)
        waitActive(firstInstance)
        assertEquals(Stage.PRE_FLOP, game.deal.stage.type)
        assertEquals(Role.FIRST, firstInstance.role)
        assertEquals(Role.BUTTON, buttonInstance.role)
        assertEquals(Role.SMALL_BLIND, smallBlindInstance.role)
        assertEquals(Role.BIG_BLIND, bigBlindInstance.role)
        waitActiveAndDoAction(firstInstance, gameId, RaiseAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(smallBlindInstance, gameId, CallAction(999))
        waitActiveAndDoAction(bigBlindInstance, gameId, CallAction(998))
        waitUntil { game.deal.stage.type == Stage.FLOP }
        waitActiveAndDoAction(smallBlindInstance, gameId, RaiseAction(1000))
        waitActiveAndDoAction(bigBlindInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(firstInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, CallAction(1000))
        waitUntil { game.deal.stage.type == Stage.TERN }
        waitActiveAndDoAction(smallBlindInstance, gameId, RaiseAction(1000))
        waitActiveAndDoAction(bigBlindInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(firstInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, CallAction(1000))
        waitUntil { game.deal.stage.type == Stage.RIVER }
        waitActiveAndDoAction(smallBlindInstance, gameId, RaiseAction(1000))
        waitActiveAndDoAction(bigBlindInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(firstInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, CallAction(1000))
    }


    @Test
    fun `should fold`() {
        val createGameResponse = gameService.createGame(
            CreateGameRequest(
                GameType.HOLDEM,
                6,
                gameID
            )
        )
        val gameId = createGameResponse.gameId
        assertTrue(createGameResponse.success)
        assertNotNull(gameId)

        val buttonInstance = Instance("test-01")
        val smallBlindInstance = Instance("test-02")
        val bigBlindInstance = Instance("test-03")
        val firstInstance = Instance("test-04")

        with(instanceService) {
            addInstances(
                gameId,
                listOf(
                    buttonInstance, smallBlindInstance,
                    bigBlindInstance, firstInstance
                )
            )
        }

        gameService.startGame(StartGameRequest(gameId = gameID))
        val game = gameStorage.getById(gameId)
        waitActive(firstInstance)
        assertEquals(Stage.PRE_FLOP, game.deal.stage.type)
        assertEquals(Role.FIRST, firstInstance.role)
        assertEquals(Role.BUTTON, buttonInstance.role)
        assertEquals(Role.SMALL_BLIND, smallBlindInstance.role)
        assertEquals(Role.BIG_BLIND, bigBlindInstance.role)
        waitActiveAndDoAction(firstInstance, gameId, RaiseAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, CallAction(1000))
        waitActiveAndDoAction(smallBlindInstance, gameId, FoldAction())
        waitActiveAndDoAction(bigBlindInstance, gameId, FoldAction())
        waitUntil { game.deal.stage.type == Stage.FLOP }
        waitActiveAndDoAction(buttonInstance, gameId, RaiseAction(1000))
    }

    private fun waitActiveAndDoAction(instance3: Instance, gameId: UUID, action: Action) {
        waitActive(instance3)
        assertTrue(instance3.active)
        doAction(gameId, instance3, action)
    }

    private fun doAction(gameId: UUID, instance: Instance, action: Action) {
        actionService.doAction(
            gameId,
            instance.instanceName,
            action
        )
    }


    fun waitUntil(predicate: () -> Boolean) {
        Awaitility.await()
            .atMost(2, TimeUnit.SECONDS)
            .until { predicate.invoke() }
    }


    fun waitActive(instance: Instance) {
        Awaitility.await()
            .atMost(222, TimeUnit.SECONDS)
            .until { instance.active }
    }

}
