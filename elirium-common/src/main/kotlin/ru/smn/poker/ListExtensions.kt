package ru.smn.poker

import ru.smn.poker.actions.Role
import ru.smn.poker.dto.Instance

fun List<Instance>.getNextIndexForRole(role: Role): Int {
    return this.indexOfFirst { instance -> instance.role == role }.run {
        if (this == lastIndex) {
            return@run 0
        }
        return@run this.inc()
    }
}

fun List<Instance>.hasRole(vararg role: Role): Boolean {
    return this.map { instance -> instance.role }
        .any { pos -> role.contains(pos) }
}

fun MutableList<Instance>.distributeRoles(): MutableList<Instance> {
    if (this.isEmpty() || this.size < 2) throw RuntimeException("list is not enough size")

    if (!hasRole(Role.BUTTON, Role.SMALL_BLIND, Role.BIG_BLIND)) {
        return apply {
            this[0].role = Role.BUTTON
            this[1].role = Role.SMALL_BLIND
            this[2].role = Role.BIG_BLIND
        }
    }

    val nextIndexForButton = getNextIndexForRole(Role.BUTTON)
    val nextIndexForSmallBlind = getNextIndexForRole(Role.SMALL_BLIND)
    val nextIndexForBigBlind = getNextIndexForRole(Role.BIG_BLIND)

    this.forEach { instance -> instance.role = Role.EMPTY }

    this[nextIndexForButton].role = Role.BUTTON
    this[nextIndexForSmallBlind].role = Role.SMALL_BLIND
    this[nextIndexForBigBlind].role = Role.BIG_BLIND
    return this
}