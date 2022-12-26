package ru.smn.poker.actions

class NoAction : Action {
    override fun count() = 0L
    override val type = ActionType.NO_ACTION
}
