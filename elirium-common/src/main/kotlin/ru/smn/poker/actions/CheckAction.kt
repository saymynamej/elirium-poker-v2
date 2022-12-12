package ru.smn.poker.actions

class CheckAction(private val count: Long) : Action {
    override fun count() = this.count

    override val type = ActionType.CHECK
}