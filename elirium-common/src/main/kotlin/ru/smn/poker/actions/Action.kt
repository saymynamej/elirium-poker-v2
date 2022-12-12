package ru.smn.poker.actions

interface Action {
    fun count(): Long
    val type: ActionType
}