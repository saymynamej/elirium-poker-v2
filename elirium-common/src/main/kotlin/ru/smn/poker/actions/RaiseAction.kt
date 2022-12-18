package ru.smn.poker.actions

data class RaiseAction(override val count: Long) : CountAction {
    override val type = ActionType.RAISE
}