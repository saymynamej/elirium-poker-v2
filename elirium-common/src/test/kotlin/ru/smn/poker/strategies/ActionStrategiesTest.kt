package ru.smn.poker.strategies

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.smn.poker.actions.*
import ru.smn.poker.actions.strategies.*
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.dto.StageStatus
import ru.smn.poker.exception.ForbiddenActionException
import ru.smn.poker.spring.ActionStrategiesConfiguration
import java.util.*

@SpringBootTest(
    classes = [
        ActionFactory::class,
        BetStrategy::class,
        CallStrategy::class,
        CheckStrategy::class,
        FoldStrategy::class,
        RaiseStrategy::class,
        AllInStrategy::class,
        ActionStrategiesConfiguration::class
    ]
)
class ActionStrategiesTest {

    @Autowired
    private lateinit var actionFactory: ActionFactory

    @Test
    fun `should throw exception when check cause last bet is not correct`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.CHECK)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CheckAction(),
                        role = Role.FIFTH
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 4L,
                        stage = StageStatus(type = Stage.PRE_FLOP, false)
                    )
                )
        }
    }


    @Test
    fun `should throw exception when check cause instance in not big blind`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.CHECK)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CheckAction(),
                        role = Role.FIFTH
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 2L,
                        stage = StageStatus(type = Stage.PRE_FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should not throw exception when check cause stage is pre flop and bb have correct size 2`() {
        assertDoesNotThrow {
            actionFactory.getByActionType(ActionType.CHECK)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CheckAction(),
                        role = Role.BIG_BLIND
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 2L,
                        stage = StageStatus(type = Stage.PRE_FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should not throw exception when check cause stage is pre flop and bb have correct size`() {
        assertDoesNotThrow {
            actionFactory.getByActionType(ActionType.CHECK)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CheckAction(),
                        role = Role.BIG_BLIND
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 1L,
                        stage = StageStatus(type = Stage.PRE_FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should throw exception when check cause stage is pre flop`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.CHECK)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CheckAction()
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 0L,
                        stage = StageStatus(type = Stage.PRE_FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should throw exception when check cause stage is post flop and last bet is not 0`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.CHECK)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CheckAction()
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 200,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should not throw exception when call cause count is not enough`() {
        assertDoesNotThrow {
            actionFactory.getByActionType(ActionType.CALL)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CallAction(200)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 200,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should throw exception when call cause count is not enough`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.CALL)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = CallAction(100)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 200,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should not throw exception when raise cause count is enough`() {
        assertDoesNotThrow {
            actionFactory.getByActionType(ActionType.RAISE)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = RaiseAction(200)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 100L,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should throw exception when raise cause count is not enough`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.RAISE)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = RaiseAction(150)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 100L,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }


    @Test
    fun `should not throw exception when bet cause last bet is 0`() {
        assertDoesNotThrow {
            actionFactory.getByActionType(ActionType.BET)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = BetAction(100)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 0L,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }


    @Test
    fun `should throw exception when bet cause stage is not postflop`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.BET)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = BetAction(100)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 0L,
                        stage = StageStatus(type = Stage.PRE_FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should throw exception when bet cause last bet is not 0`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.BET)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = BetAction(100)
                    ), Deal(
                        UUID.randomUUID(),
                        lastBet = 100L,
                        stage = StageStatus(type = Stage.FLOP, false)
                    )
                )
        }
    }

    @Test
    fun `should throw exception when all in cause chips are 0`() {
        assertThrows<ForbiddenActionException> {
            actionFactory.getByActionType(ActionType.ALL_IN)
                .invoke(
                    Instance(
                        "test-01",
                        chips = 0L,
                        action = AllinAction(300)
                    ), Deal(UUID.randomUUID())
                )
        }
    }

    @Test
    fun `should not throw exception when all in cause chips more than 0`() {
        assertDoesNotThrow {
            actionFactory.getByActionType(ActionType.ALL_IN)
                .invoke(
                    Instance(
                        "test-01", chips = 1000L,
                        action = AllinAction(300)
                    ), Deal(UUID.randomUUID())
                )
        }

    }
}