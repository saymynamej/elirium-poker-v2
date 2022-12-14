package ru.smn.poker.core

import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance

interface ActionHandler {
    suspend fun handle(deal: Deal, instance: Instance)
    fun handleBlinds(deal: Deal, instances: MutableList<Instance>)
}
