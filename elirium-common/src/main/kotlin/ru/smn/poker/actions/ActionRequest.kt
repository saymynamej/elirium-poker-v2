package ru.smn.poker.actions

data class ActionRequest(
    val count: Long,
    val playerName: String,
    val actionType: ActionType
)