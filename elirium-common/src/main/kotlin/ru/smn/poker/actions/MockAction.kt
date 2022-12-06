package ru.smn.poker.actions

class MockAction : Action {
    override fun count() = 0L
    override fun actionType() = ActionType.MOCK
}