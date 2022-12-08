package ru.smn.poker.dto

import java.util.UUID

data class Deal(
    val gameId: UUID,
    var bigBlindBet: Long = 1,
    var smallBlindBet: Long = 2,
    var bank: Long = 0,
    var stage: StageStatus = StageStatus(Stage.NONE, true),
    var run: Boolean = true,
)