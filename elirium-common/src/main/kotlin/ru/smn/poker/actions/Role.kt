package ru.smn.poker.actions

enum class Role(val grade: Int) {
    BUTTON(0),
    SMALL_BLIND(1),
    BIG_BLIND(2),
    FIRST(3),
    SECOND(4),
    THIRD(5),
    FOURTH(6),
    FIFTH(7),
    SIXTH(8),
    EMPTY(-1);

    companion object {
        fun findByGrade(grade: Int) = Role.values().first { role -> role.grade == grade }
    }

}