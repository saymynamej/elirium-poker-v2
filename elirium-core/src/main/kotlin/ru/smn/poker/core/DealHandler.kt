package ru.smn.poker.core

import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance

interface DealHandler {
    fun handle(instances: MutableList<Instance>, deal: Deal)
}