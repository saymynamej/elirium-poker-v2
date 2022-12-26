package ru.smn.poker.actions

class AllinAction(private val count: Long) : Action {
    override fun count() = this.count
    override val type = ActionType.ALL_IN
}
