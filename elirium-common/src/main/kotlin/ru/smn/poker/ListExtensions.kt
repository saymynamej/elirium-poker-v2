package ru.smn.poker

import ru.smn.poker.actions.Position
import ru.smn.poker.dto.Instance


fun List<Instance>.distributeRoles(): List<Instance> {
    if (this.isEmpty() || this.size < 2) throw RuntimeException("list is not enough size")
    return apply {
        this[0].position = Position.BUTTON
        this[1].position = Position.SMALL_BLIND
        this[2].position = Position.BIG_BLIND
    }
}