package ru.smn.poker.core

import org.awaitility.Awaitility
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.smn.poker.actions.*
import ru.smn.poker.combination.CardContainer
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
        GameSetup::class,
        GameHandlerImpl::class,
        CardContainer::class
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
    fun `test with all in actions`() {
        createDefaultGame()
        val game = gameStorage.getById(gameID)
        val gameId = game.gameId
        waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
        assertEquals(Stage.PRE_FLOP, game.deal.stage.type)
        assertEquals(Role.FIRST, firstInstance.role)
        assertEquals(Role.BUTTON, buttonInstance.role)
        assertEquals(Role.SMALL_BLIND, smallBlindInstance.role)
        assertEquals(Role.BIG_BLIND, bigBlindInstance.role)
        waitActiveAndDoAction(firstInstance, gameId, AllinAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, AllinAction(1000))
        waitActiveAndDoAction(smallBlindInstance, gameId, AllinAction(999))
        waitActiveAndDoAction(bigBlindInstance, gameId, AllinAction(998))
        waitUntil { game.deal.stage.type == Stage.FLOP }
        waitUntil { game.deal.stage.type == Stage.TERN }
        waitUntil { game.deal.stage.type == Stage.RIVER }
    }

    @Test
    fun `test with call actions`() {
        createDefaultGame()
        val game = gameStorage.getById(gameID)
        val gameId = game.gameId
        waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
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
        waitUntil { game.deal.finished }
    }

    @Test
    fun `test with fold actions`() {
        createDefaultGame()
        val game = gameStorage.getById(gameID)
        val gameId = game.gameId
        waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
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
        waitActiveAndDoAction(firstInstance, gameId, BetAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, BetAction(1000))
        waitUntil { game.deal.stage.type == Stage.TERN }
        waitActiveAndDoAction(firstInstance, gameId, BetAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, BetAction(1000))
        waitUntil { game.deal.stage.type == Stage.RIVER }
        waitActiveAndDoAction(firstInstance, gameId, BetAction(1000))
        waitActiveAndDoAction(buttonInstance, gameId, BetAction(1000))
        waitUntil { game.deal.finished }
    }


    private fun createDefaultGame() {
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

        val listOfInstances = listOf(
            Instance("test-01"),
            Instance("test-02"),
            Instance("test-03"),
            Instance("test-04")
        )

        with(instanceService) {
            addInstances(
                gameId,
                listOfInstances
            )
        }

        gameService.startGame(StartGameRequest(gameID))
    }

    private fun waitActiveAndDoAction(instance: Instance, gameId: UUID, action: Action) {
        waitActive(instance)
        assertTrue(instance.active)
        doAction(gameId, instance, action)
    }

    private fun doAction(gameId: UUID, instance: Instance, action: Action) {
        actionService.doAction(
            gameId,
            instance.instanceName,
            action
        )
    }


    private fun waitUntil(predicate: () -> Boolean) {
        Awaitility.await()
            .atMost(2, TimeUnit.SECONDS)
            .until { predicate.invoke() }
    }


    private fun waitActive(instance: Instance) {
        Awaitility.await()
            .atMost(2, TimeUnit.SECONDS)
            .until { instance.active }
    }

}
