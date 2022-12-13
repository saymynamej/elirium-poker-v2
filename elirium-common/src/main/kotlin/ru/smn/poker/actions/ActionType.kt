package ru.smn.poker.actions


enum class ActionType {
    CALL,
    BET,
    FOLD,
    RAISE,
    ALL_IN,
    CHECK,
    NO_ACTION;

    fun toAction(count: Long = 0): Action {
        return when (this) {
            CALL -> CallAction(count)
            RAISE -> RaiseAction(count)
            CHECK -> CheckAction()
            BET -> BetAction(count)
            ALL_IN -> AllinAction(count)
            NO_ACTION -> NoAction()
            FOLD -> FoldAction()
        }
    }

}