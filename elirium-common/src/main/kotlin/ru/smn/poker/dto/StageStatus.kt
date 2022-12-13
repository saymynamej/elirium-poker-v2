package ru.smn.poker.dto

data class StageStatus(var type: Stage = Stage.PRE_FLOP, var finished: Boolean) {
    fun next(): Stage {
        return when (type) {
            Stage.PRE_FLOP -> Stage.FLOP
            Stage.FLOP -> Stage.TERN
            Stage.TERN -> Stage.RIVER
            Stage.RIVER -> Stage.FLOP
        }
    }
}