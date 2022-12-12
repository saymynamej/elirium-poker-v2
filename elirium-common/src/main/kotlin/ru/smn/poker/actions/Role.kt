package ru.smn.poker.actions

enum class Role(
    val grade: Int,
    val beforeFlopGrade: Int,
    val afterFlopGrade: Int,
) {
    BUTTON(0, 6, 8),
    SMALL_BLIND(1, 7, 0),
    BIG_BLIND(2, 8, 1),
    FIRST(3, 0, 2),
    SECOND(4, 1, 3),
    THIRD(5, 2, 4),
    FOURTH(6, 3, 5),
    FIFTH(7, 4, 6),
    SIXTH(8, 5, 7),
    EMPTY(-1, -1, -1);

    companion object {
        fun findByGrade(grade: Int) = Role.values().first { role -> role.grade == grade }
    }

}