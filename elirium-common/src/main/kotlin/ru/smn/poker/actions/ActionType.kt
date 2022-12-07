package ru.smn.poker.actions

enum class ActionType {
    CALL,
    BET,
    RAISE,
    ALL_IN,
    CHECK,
    MOCK;

    fun toAction(count: Long = 0): Action {
        return when (this) {
            CALL -> CallAction(count)
            RAISE -> RaiseAction(count)
            CHECK -> CheckAction(count)
            BET -> BetAction(count)
            ALL_IN -> AllinAction(count)
            MOCK -> MockAction()
        }
    }

}