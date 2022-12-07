package ru.smn.poker.dto

import ru.smn.poker.actions.Position


class Instance(
    val instanceName: String,
    var active: Boolean,
    var position: Position = Position.EMPTY
)