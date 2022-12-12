package ru.smn.poker.actions

interface Action {
    fun count(): Long
    val actionType: ActionType
}