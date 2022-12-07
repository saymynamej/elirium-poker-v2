package ru.smn.poker.actions

class RaiseAction(private val count: Long) : Action {
    override fun count() = this.count

    override fun actionType() = ActionType.RAISE
}