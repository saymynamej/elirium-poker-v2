package ru.smn.poker.core

import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Instance
import java.util.*


class GameCore(
    private val gameId: UUID,
    private val instances: MutableList<Instance>
) {
    fun start() {
        val distributeRoles = this.instances.shuffled().distributeRoles()
    }

    fun addPlayer(instance: Instance) {
        instances.add(instance)
    }

}