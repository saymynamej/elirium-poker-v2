package ru.smn.poker.dto

import ru.smn.combination.data.Card
import java.util.UUID

data class Deal(
    val gameId: UUID,
    val bigBlindBet: Long = 2,
    val smallBlindBet: Long = 1,
    val stage: StageStatus = StageStatus(Stage.PRE_FLOP, true),
    val flop1: Card? = null,
    val flop2: Card? = null,
    val flop3: Card? = null,
    val tern: Card? = null,
    val river: Card? = null,
    var bank: Long = 0,
)