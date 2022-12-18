package ru.smn.poker.actions

class AllinAction(override val count: Long) : CountAction {
    override val type = ActionType.ALL_IN
}