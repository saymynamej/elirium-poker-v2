package ru.smn.poker.actions

class FoldAction : Action {
    override fun count() = 0L

    override val type = ActionType.FOLD
}
