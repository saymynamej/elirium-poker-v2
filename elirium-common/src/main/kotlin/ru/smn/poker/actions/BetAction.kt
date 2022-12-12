package ru.smn.poker.actions

data class BetAction(private val count: Long) : Action {
    override fun count() = this.count
    override val type = ActionType.BET
}