package ru.smn

import org.junit.jupiter.api.Test
import ru.smn.poker.actions.Role
import ru.smn.poker.anyHasRoles
import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Instance
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class InstanceExtensionsTest {

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
        val instances = listOf(
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
        val instances = listOf(
            Instance("test-1", role = Role.BUTTON),
            Instance("test-2", role = Role.SMALL_BLIND),
            Instance("test-3", role = Role.BIG_BLIND)
        )

        val hasRoles = instances.anyHasRoles(Role.BUTTON)
        assertTrue(hasRoles, "incorrect definition role")
    }

    @Test
    fun shouldNotDefineButton() {
        val instances = listOf(
            Instance("test-0", role = Role.EMPTY)
        )
        val hasRoles = instances.anyHasRoles(Role.BUTTON)
        assertFalse(hasRoles, "incorrect definition role")
    }

}