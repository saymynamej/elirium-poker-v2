package ru.smn.poker

import org.junit.jupiter.api.Test
import ru.smn.poker.actions.Action
import ru.smn.poker.actions.CallAction
import ru.smn.poker.actions.FoldAction
import ru.smn.poker.actions.Role
import ru.smn.poker.dto.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class InstanceExtensionsTest {


    @Test
    fun shouldGetRightPlayer() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.EMPTY),
            Instance("test-1", role = Role.EMPTY).apply {
                history[Stage.PRE_FLOP]!!.add(FoldAction())
            },
            Instance("test-2", role = Role.EMPTY),
            Instance("test-3", role = Role.EMPTY),
            Instance("test-4", role = Role.EMPTY),
            Instance("test-5", role = Role.EMPTY),
            Instance("test-6", role = Role.EMPTY),
            Instance("test-7", role = Role.EMPTY),
            Instance("test-8", role = Role.EMPTY),
        )

        val nextFunction = instances.next()
        assertEquals("test-0", nextFunction().instanceName)
        assertEquals("test-2", nextFunction().instanceName)
        assertEquals("test-3", nextFunction().instanceName)
        assertEquals("test-4", nextFunction().instanceName)
        assertEquals("test-5", nextFunction().instanceName)
        assertEquals("test-6", nextFunction().instanceName)
        assertEquals("test-7", nextFunction().instanceName)
        assertEquals("test-8", nextFunction().instanceName)
        assertEquals("test-0", nextFunction().instanceName)
    }


    @Test
    fun shouldTwistRoles() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.EMPTY),
            Instance("test-1", role = Role.EMPTY),
            Instance("test-2", role = Role.EMPTY),
            Instance("test-3", role = Role.EMPTY),
            Instance("test-4", role = Role.EMPTY),
            Instance("test-5", role = Role.EMPTY),
            Instance("test-6", role = Role.EMPTY),
            Instance("test-7", role = Role.EMPTY),
            Instance("test-8", role = Role.EMPTY),
        )

        instances.distributeRoles()
        assertTrue { instances[0].role == Role.BUTTON }
        assertTrue { instances[1].role == Role.SMALL_BLIND }
        assertTrue { instances[2].role == Role.BIG_BLIND }
        assertTrue { instances[3].role == Role.FIRST }
        assertTrue { instances[4].role == Role.SECOND }
        assertTrue { instances[5].role == Role.THIRD }
        assertTrue { instances[6].role == Role.FOURTH }
        assertTrue { instances[7].role == Role.FIFTH }
        assertTrue { instances[8].role == Role.SIXTH }
    }


    @Test
    fun shouldTwistRolesInTheEnd() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.BUTTON),
            Instance("test-1", role = Role.SMALL_BLIND),
            Instance("test-2", role = Role.BIG_BLIND),
            Instance("test-3", role = Role.FIRST),
            Instance("test-4", role = Role.SECOND),
            Instance("test-5", role = Role.THIRD),
            Instance("test-6", role = Role.FOURTH),
            Instance("test-7", role = Role.FIFTH),
            Instance("test-8", role = Role.SIXTH),
        )

        instances.distributeRoles()
        assertTrue { instances[0].role == Role.SIXTH }
        assertTrue { instances[1].role == Role.BUTTON }
        assertTrue { instances[2].role == Role.SMALL_BLIND }
        assertTrue { instances[3].role == Role.BIG_BLIND }
        assertTrue { instances[4].role == Role.FIRST }
        assertTrue { instances[5].role == Role.SECOND }
        assertTrue { instances[6].role == Role.THIRD }
        assertTrue { instances[7].role == Role.FOURTH }
        assertTrue { instances[8].role == Role.FIFTH }
    }

    @Test
    fun shouldDefineButton() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.EMPTY),
            Instance("test-1", role = Role.BUTTON),
            Instance("test-2", role = Role.SMALL_BLIND),
            Instance("test-3", role = Role.BIG_BLIND)
        )

        val hasRoles = instances.anyHasRoles(Role.EMPTY, Role.SMALL_BLIND, Role.BIG_BLIND, Role.BUTTON)
        assertTrue(hasRoles, "incorrect definition role")
    }

    @Test
    fun shouldDefineSingleRole() {
        val instances = mutableListOf(
            Instance("test-1", role = Role.BUTTON),
            Instance("test-2", role = Role.SMALL_BLIND),
            Instance("test-3", role = Role.BIG_BLIND)
        )

        val hasRoles = instances.anyHasRoles(Role.BUTTON)
        assertTrue(hasRoles, "incorrect definition role")
    }

    @Test
    fun shouldNotDefineButton() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.EMPTY)
        )
        val hasRoles = instances.anyHasRoles(Role.BUTTON)
        assertFalse(hasRoles, "incorrect definition role")
    }

    @Test
    fun shouldEveryOneHasTheSameBet() {
        val history: Map<Stage, MutableList<Action>> = mapOf(
            Stage.PRE_FLOP to mutableListOf(CallAction(1000), CallAction(2000))
        )
        assertTrue {
            mutableListOf(
                Instance("test-01", history = history),
                Instance("test-02", history = history),
                Instance("test-03", history = history),
                Instance("test-04", history = history)
            ).everyoneHasTheSameBet(Stage.PRE_FLOP)
        }
    }

    @Test
    fun shouldNotEveryOneHasTheSameBetWhenBetsZero() {
        val history: Map<Stage, MutableList<Action>> = mapOf(
            Stage.PRE_FLOP to mutableListOf(CallAction(0), CallAction(0))
        )

        assertFalse {
            mutableListOf(
                Instance("test-01", history = history),
                Instance("test-02", history = history),
                Instance("test-03", history = history),
                Instance("test-04", history = history)
            ).everyoneHasTheSameBet(Stage.PRE_FLOP)
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
            mutableListOf(
                Instance("test-01", history = firstHistory),
                Instance("test-02", history = secondHistory),
                Instance("test-03", history = firstHistory),
                Instance("test-04", history = secondHistory)
            ).everyoneHasTheSameBet(Stage.PRE_FLOP)
        }
    }

}