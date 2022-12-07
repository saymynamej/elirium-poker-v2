package ru.smn.poker.core

import ru.smn.poker.actions.Action
import ru.smn.poker.dto.Instance

interface GameHandler {
    fun handle(action: Action, instance: Instance)
}
