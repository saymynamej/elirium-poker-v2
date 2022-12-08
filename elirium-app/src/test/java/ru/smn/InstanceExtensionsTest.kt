package ru.smn

import org.junit.jupiter.api.Test
import ru.smn.poker.actions.Role
import ru.smn.poker.dto.Instance
import ru.smn.poker.hasRole
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class InstanceExtensionsTest {
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
    fun shouldNotDefineButton() {
        val instances = listOf(
            Instance("test-0", role = Role.EMPTY)
        )
        val hasRoles = instances.hasRole(Role.BUTTON)
        assertFalse(hasRoles, "incorrect definition role")
    }

}