package ru.smn.poker.actions

interface Action {
    fun count(): Long
    fun actionType(): ActionType
}