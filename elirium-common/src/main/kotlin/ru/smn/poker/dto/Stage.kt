package ru.smn.poker.dto

enum class Stage(val grade: Int) {
    PRE_FLOP(0),
    FLOP(1),
    TERN(2),
    RIVER(3);

    fun isPostFlop() = this != PRE_FLOP
    fun isPreFlop() = this == PRE_FLOP
}
