package ru.smn.poker.dto

import java.util.UUID

data class Data(
    val gameId: UUID,
    var bigBlindBet: Long = 0,
    var smallBlindBet: Long = 0,
    var bank: Long = 0,
    var stage: Stage = Stage.PREFLOP,
)