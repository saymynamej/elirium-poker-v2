package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance

interface GameHandler {
    fun waitAndHandle(deal: Deal, action: Action, instance: Instance)
    fun handleBlinds(deal: Deal, instances: MutableList<Instance>)
}
