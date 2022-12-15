package ru.smn.poker.game

import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import java.util.*

data class StartGameResponse(
    val gameId: UUID,
    val deal: Deal,
    val instances: List<Instance>
)