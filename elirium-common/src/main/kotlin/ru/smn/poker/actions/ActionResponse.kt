package ru.smn.poker.actions

import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import java.util.*

data class ActionResponse(
    val count: Long,
    val gameId: UUID,
    val playerName: String,
    val deal: Deal,
    val instances: List<Instance>,
)