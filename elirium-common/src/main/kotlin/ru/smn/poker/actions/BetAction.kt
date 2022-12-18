package ru.smn.poker.actions

data class BetAction(override val count: Long) : CountAction {
    override val type = ActionType.BET
}