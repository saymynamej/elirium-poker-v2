package ru.smn.poker.actions

data class CallAction(
    val count: Long,
) : Action {

    override fun count() = this.count

    override val type = ActionType.CALL
}
