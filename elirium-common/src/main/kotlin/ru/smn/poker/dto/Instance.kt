package ru.smn.poker.dto

import ru.smn.poker.actions.Position


class Instance(
    val name: String,
    val active: Boolean,
    var position: Position = Position.EMPTY
)