package ru.smn.poker.core

import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import java.util.*

interface DealCustomizer {
    fun customize(gameId: UUID, deal: Deal, instances: MutableList<Instance>): Deal
}