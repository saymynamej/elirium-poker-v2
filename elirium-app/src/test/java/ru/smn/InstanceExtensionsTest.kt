package ru.smn

import org.junit.jupiter.api.Test
import ru.smn.poker.actions.Role
import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Instance
import ru.smn.poker.hasRole
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class InstanceExtensionsTest {

    @Test
    fun shouldTwistRoles() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.BUTTON),
            Instance("test-1", role = Role.SMALL_BLIND),
            Instance("test-2", role = Role.BIG_BLIND),
            Instance("test-3", role = Role.EMPTY),
            Instance("test-4", role = Role.EMPTY),
            Instance("test-5", role = Role.EMPTY),
            Instance("test-6", role = Role.EMPTY),
            Instance("test-7", role = Role.EMPTY),
            Instance("test-8", role = Role.EMPTY),
        )

        instances.distributeRoles()
        assertTrue { instances[0].role == Role.EMPTY }
        assertTrue { instances[1].role == Role.BUTTON }
        assertTrue { instances[2].role == Role.SMALL_BLIND }
        assertTrue { instances[3].role == Role.BIG_BLIND }
        assertTrue { instances[4].role == Role.EMPTY }
        assertTrue { instances[5].role == Role.EMPTY }
        assertTrue { instances[6].role == Role.EMPTY }
        assertTrue { instances[7].role == Role.EMPTY }
        assertTrue { instances[8].role == Role.EMPTY }
    }


    @Test
    fun shouldTwistRolesInTheEnd() {
        val instances = mutableListOf(
            Instance("test-0", role = Role.BUTTON),
            Instance("test-1", role = Role.SMALL_BLIND),
            Instance("test-2", role = Role.BIG_BLIND),
            Instance("test-3", role = Role.EMPTY),
            Instance("test-4", role = Role.EMPTY),
            Instance("test-5", role = Role.EMPTY),
            Instance("test-6", role = Role.BUTTON),
            Instance("test-7", role = Role.SMALL_BLIND),
            Instance("test-8", role = Role.BIG_BLIND),
        )

        instances.distributeRoles()
        assertTrue { instances[0].role == Role.BIG_BLIND }
        assertTrue { instances[1].role == Role.EMPTY }
        assertTrue { instances[2].role == Role.EMPTY }
        assertTrue { instances[3].role == Role.EMPTY }
        assertTrue { instances[4].role == Role.EMPTY }
        assertTrue { instances[5].role == Role.EMPTY }
        assertTrue { instances[6].role == Role.EMPTY }
        assertTrue { instances[7].role == Role.BUTTON }
        assertTrue { instances[8].role == Role.SMALL_BLIND }
    }

    @Test
    fun shouldDefineButton() {
        val instances = listOf(
            Instance("test-0", role = Role.EMPTY),
            Instance("test-1", role = Role.BUTTON),
            Instance("test-2", role = Role.SMALL_BLIND),
            Instance("test-3", role = Role.BIG_BLIND)
        )

        val hasRoles = instances.hasRole(Role.EMPTY, Role.SMALL_BLIND, Role.BIG_BLIND, Role.BUTTON)
        assertTrue(hasRoles, "incorrect definition role")
    }

    @Test
    fun shouldDefineSingleRole() {
        val instances = listOf(
            Instance("test-1", role = Role.BUTTON),
            Instance("test-2", role = Role.SMALL_BLIND),
            Instance("test-3", role = Role.BIG_BLIND)
        )

        val hasRoles = instances.hasRole(Role.BUTTON)
        assertTrue(hasRoles, "incorrect definition role")
    }

    @Test
    fun shouldNotDefineButton() {
        val instances = listOf(
            Instance("test-0", role = Role.EMPTY)
        )
        val hasRoles = instances.hasRole(Role.BUTTON)
        assertFalse(hasRoles, "incorrect definition role")
    }

}