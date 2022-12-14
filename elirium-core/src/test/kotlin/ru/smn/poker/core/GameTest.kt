package ru.smn.poker.core

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.smn.poker.actions.*
import ru.smn.poker.combination.CardContainer
import ru.smn.poker.dto.Stage
import ru.smn.poker.helper.ActionHelper
import java.util.*
import kotlin.test.Test

@SpringBootTest(
    classes = [
        GameServiceImpl::class,
        GameStorage::class,
        InstanceService::class,
        ActionServiceImpl::class,
        GameSetup::class,
        ActionHandlerImpl::class,
        CardContainer::class,
        ActionHelper::class,
        GameCreator::class
    ]
)
@ExtendWith(SpringExtension::class)
class GameTest {

    @Autowired
    private lateinit var actionHelper: ActionHelper

    @Autowired
    private lateinit var gameStorage: GameStorage

    @Autowired
    private lateinit var gameCreator: GameCreator


    @Test
    fun `scenario 1`() {
        val gameId = gameCreator.createDefaultGame(6)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val secondInstance = game.instances.first { instance -> instance.role == Role.SECOND }
        val thirdInstance = game.instances.first { instance -> instance.role == Role.THIRD }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }

        with(actionHelper) {
            waitUntil { game.deal.stage.type == Stage.PRE_FLOP }
            waitActiveAndDoAction(firstInstance, gameId, CallAction(2))
            waitActiveAndDoAction(secondInstance, gameId, FoldAction())
            waitActiveAndDoAction(thirdInstance, gameId, FoldAction())
            waitActiveAndDoAction(buttonInstance, gameId, FoldAction())
            waitActiveAndDoAction(smallBlindInstance, gameId, FoldAction())
            waitActiveAndDoAction(bigBlindInstance, gameId, CheckAction())
            waitUntil { game.deal.stage.type == Stage.FLOP }
            waitActiveAndDoAction(bigBlindInstance, gameId, BetAction(10))
            waitActiveAndDoAction(firstInstance, gameId, CallAction(10))
            waitUntil { game.deal.stage.type == Stage.TERN }
            waitActiveAndDoAction(bigBlindInstance, gameId, BetAction(10))
            waitActiveAndDoAction(firstInstance, gameId, CallAction(10))
            waitUntil { game.deal.stage.type == Stage.RIVER }
            waitActiveAndDoAction(bigBlindInstance, gameId, BetAction(10))
            waitActiveAndDoAction(firstInstance, gameId, CallAction(10))
            waitUntil { game.deal.finished }
        }
    }

    @Test
    fun `scenario 2`() {
        val gameId = gameCreator.createDefaultGame(4)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
        with(actionHelper) {
            waitActiveAndDoAction(firstInstance, gameId, CallAction(2))
            waitActiveAndDoAction(buttonInstance, gameId, CallAction(2))
            waitActiveAndDoAction(smallBlindInstance, gameId, CallAction(1))
            waitActiveAndDoAction(bigBlindInstance, gameId, CheckAction())
            waitUntil { game.deal.stage.type == Stage.FLOP }
            waitActiveAndDoAction(smallBlindInstance, gameId, CheckAction())
            waitActiveAndDoAction(bigBlindInstance, gameId, CheckAction())
            waitActiveAndDoAction(firstInstance, gameId, CheckAction())
            waitActiveAndDoAction(buttonInstance, gameId, CheckAction())
            waitUntil { game.deal.stage.type == Stage.TERN }
            waitActiveAndDoAction(smallBlindInstance, gameId, CheckAction())
            waitActiveAndDoAction(bigBlindInstance, gameId, CheckAction())
            waitActiveAndDoAction(firstInstance, gameId, CheckAction())
            waitActiveAndDoAction(buttonInstance, gameId, CheckAction())
            waitUntil { game.deal.stage.type == Stage.RIVER }
            waitActiveAndDoAction(smallBlindInstance, gameId, CheckAction())
            waitActiveAndDoAction(bigBlindInstance, gameId, CheckAction())
            waitActiveAndDoAction(firstInstance, gameId, CheckAction())
            waitActiveAndDoAction(buttonInstance, gameId, CheckAction())
            waitUntil { game.deal.finished }
        }

    }

    @Test
    fun `scenario 3`() {
        val gameId = gameCreator.createDefaultGame(4)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
        with(actionHelper) {
            waitActiveAndDoAction(firstInstance, gameId, RaiseAction(200))
            waitActiveAndDoAction(buttonInstance, gameId, RaiseAction(400))
            waitActiveAndDoAction(smallBlindInstance, gameId, FoldAction())
            waitActiveAndDoAction(bigBlindInstance, gameId, FoldAction())
            waitActiveAndDoAction(firstInstance, gameId, CallAction(200))
            waitUntil { game.deal.stage.type == Stage.FLOP }
            waitActiveAndDoAction(firstInstance, gameId, RaiseAction(200))
            waitActiveAndDoAction(buttonInstance, gameId, CallAction(200))
            waitUntil { game.deal.stage.type == Stage.TERN }
            waitActiveAndDoAction(firstInstance, gameId, RaiseAction(200))
            waitActiveAndDoAction(buttonInstance, gameId, CallAction(200))
            waitUntil { game.deal.stage.type == Stage.RIVER }
            waitActiveAndDoAction(firstInstance, gameId, RaiseAction(200))
            waitActiveAndDoAction(buttonInstance, gameId, CallAction(200))
            waitUntil { game.deal.finished }
        }

    }

    @Test
    fun `scenario 4`() {
        val gameId = gameCreator.createDefaultGame(4)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
        with(actionHelper) {
            waitActiveAndDoAction(firstInstance, gameId, AllinAction(1000))
            waitActiveAndDoAction(buttonInstance, gameId, AllinAction(1000))
            waitActiveAndDoAction(smallBlindInstance, gameId, AllinAction(999))
            waitActiveAndDoAction(bigBlindInstance, gameId, AllinAction(998))
            waitUntil { game.deal.finished }
        }
    }

    @Test
    fun `scenario 5`() {
        val gameId = gameCreator.createDefaultGame(4)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }
        with(actionHelper) {
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

    }

    @Test
    fun `scenario 6`() {
        val gameId = gameCreator.createDefaultGame(4)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }

        with(actionHelper) {
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

    }

    @Test
    fun `scenario 7`() {
        val gameId = gameCreator.createDefaultGame(6)
        val game = gameStorage.getById(gameId)
        actionHelper.waitUntil { game.active }
        val firstInstance = game.instances.first { instance -> instance.role == Role.FIRST }
        val secondInstance = game.instances.first { instance -> instance.role == Role.SECOND }
        val thirdInstance = game.instances.first { instance -> instance.role == Role.THIRD }
        val buttonInstance = game.instances.first { instance -> instance.role == Role.BUTTON }
        val smallBlindInstance = game.instances.first { instance -> instance.role == Role.SMALL_BLIND }
        val bigBlindInstance = game.instances.first { instance -> instance.role == Role.BIG_BLIND }

        with(actionHelper) {
            waitUntil { game.deal.stage.type == Stage.PRE_FLOP }
            waitActiveAndDoAction(firstInstance, gameId, RaiseAction(10))
            waitActiveAndDoAction(secondInstance, gameId, RaiseAction(20))
            waitActiveAndDoAction(thirdInstance, gameId, RaiseAction(40))
            waitActiveAndDoAction(buttonInstance, gameId, RaiseAction(80))
            waitActiveAndDoAction(smallBlindInstance, gameId, FoldAction())
            waitActiveAndDoAction(bigBlindInstance, gameId, FoldAction())
            waitActiveAndDoAction(firstInstance, gameId, CallAction(70))
            waitActiveAndDoAction(secondInstance, gameId, CallAction(60))
            waitActiveAndDoAction(thirdInstance, gameId, CallAction(40))
            waitUntil { game.deal.stage.type == Stage.FLOP }
        }
    }

}
