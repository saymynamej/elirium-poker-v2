package ru.smn.poker.core

import ru.smn.poker.distributeRoles
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.EliriumLogger
import java.util.*


class GameCore(
    val gameId: UUID,
    val instances: MutableList<Instance>
) {
    fun start() {
        EliriumLogger(
            "game started. id = $gameId"
        ).print()
        val distributeRoles = this.instances.shuffled().distributeRoles()
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }

}