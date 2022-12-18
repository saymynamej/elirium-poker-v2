package ru.smn.poker.dto

import ru.smn.poker.combination.data.Card
import java.util.*

data class Deal(
    var gameId: UUID,
    var bigBlindBet: Long = 2,
    var smallBlindBet: Long = 1,
    var lastBet: Long = 0,
    var flop1: Card? = null,
    var flop2: Card? = null,
    var flop3: Card? = null,
    var tern: Card? = null,
    var river: Card? = null,
    var stage: StageStatus = StageStatus(Stage.PRE_FLOP, false),
    var bank: Long = 0,
    var finished: Boolean = false,
) {

    fun nextStage(): StageStatus {
        val nexStage = stage.next()
        this.stage = StageStatus(nexStage, false)
        this.lastBet = 0L
        return this.stage
    }

}