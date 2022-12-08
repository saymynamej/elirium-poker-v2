package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.dto.Data
import ru.smn.poker.dto.Instance

interface GameHandler {
    fun handle(data: Data, action: Action, instance: Instance)
}
