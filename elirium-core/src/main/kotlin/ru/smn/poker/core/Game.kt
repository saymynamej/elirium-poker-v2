package ru.smn.poker.core

import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import java.util.*

interface Game {
    val active: Boolean

    val gameId: UUID

    val instances: MutableList<Instance>

    val deal: Deal
    suspend fun start()
    suspend fun stop()
    fun addInstance(instance: Instance)
    fun removeInstance(instance: Instance)

}