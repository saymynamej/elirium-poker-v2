package ru.smn.poker.dto

data class StageStatus(var type: Stage = Stage.NONE, var run: Boolean) {
    fun nextStage(): Stage {
        return when (type) {
            Stage.NONE -> Stage.PRE_FLOP
            Stage.PRE_FLOP -> Stage.FLOP
            Stage.FLOP -> Stage.TERN
            Stage.TERN -> Stage.RIVER
            Stage.RIVER -> Stage.RIVER
        }
    }
}