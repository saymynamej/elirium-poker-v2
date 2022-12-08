package ru.smn.poker.dto

import ru.smn.combination.data.Card
import ru.smn.poker.actions.Role


data class Instance(
    val instanceName: String,
    var active: Boolean = false,
    var timeBank: Int = 120,
    var role: Role = Role.EMPTY,
    var cards: List<Card>
)