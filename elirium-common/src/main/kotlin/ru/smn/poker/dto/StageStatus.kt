package ru.smn.poker.dto

data class StageStatus(var type: Stage = Stage.PRE_FLOP, var run: Boolean) {
    fun defineStage(): Stage {
        return when (type) {
            Stage.PRE_FLOP -> Stage.FLOP
            Stage.FLOP -> Stage.TERN
            Stage.TERN -> Stage.RIVER
            Stage.RIVER -> Stage.PRE_FLOP
        }
    }
}