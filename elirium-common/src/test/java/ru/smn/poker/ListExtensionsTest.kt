package ru.smn.poker

import org.junit.jupiter.api.Test
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.CallAction
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ListExtensionsTest {

    @Test
    fun shouldEveryOneHasTheSameBet() {
        val history: Map<Stage, MutableList<Action>> = mapOf(
            Stage.PRE_FLOP to mutableListOf(CallAction(1000), CallAction(2000))
        )
        assertTrue {
            listOf(
                Instance("test-01", history = history),
                Instance("test-02", history = history),
                Instance("test-03", history = history),
                Instance("test-04", history = history)
            ).everyOneHasTheSameBet(Stage.PRE_FLOP)
        }
    }

    @Test
    fun shouldNotEveryOneHasTheSameBetWhenBetsZero() {
        val history: Map<Stage, MutableList<Action>> = mapOf(
            Stage.PRE_FLOP to mutableListOf(CallAction(0), CallAction(0))
        )

        assertFalse {
            listOf(
                Instance("test-01", history = history),
                Instance("test-02", history = history),
                Instance("test-03", history = history),
                Instance("test-04", history = history)
            ).everyOneHasTheSameBet(Stage.PRE_FLOP)
        }
    }

    @Test
    fun shouldNotEveryOneHasTheSameBet() {
        val firstHistory: Map<Stage, MutableList<Action>> = mapOf(
            Stage.PRE_FLOP to mutableListOf(CallAction(1000), CallAction(2000))
        )

        val secondHistory: Map<Stage, MutableList<Action>> = mapOf(
            Stage.PRE_FLOP to mutableListOf(CallAction(1000), CallAction(3000))
        )

        assertFalse {
            listOf(
                Instance("test-01", history = firstHistory),
                Instance("test-02", history = secondHistory),
                Instance("test-03", history = firstHistory),
                Instance("test-04", history = secondHistory)
            ).everyOneHasTheSameBet(Stage.PRE_FLOP)
        }
    }
}