package ru.smn.poker

import ru.smn.poker.actions.Role
import ru.smn.poker.dto.Instance

fun List<Instance>.hasRole(vararg role: Role): Boolean {
    return this.map { instance -> instance.role }
        .all { pos -> role.contains(pos) }
}

fun List<Instance>.distributeRoles(): List<Instance> {
    return apply {
        this[0].role = Role.BUTTON
        this[1].role = Role.SMALL_BLIND
        this[2].role = Role.BIG_BLIND
    }
}