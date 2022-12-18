package ru.smn.poker.actions

data class CallAction(override val count: Long) : CountAction {
    override val type = ActionType.CALL
}