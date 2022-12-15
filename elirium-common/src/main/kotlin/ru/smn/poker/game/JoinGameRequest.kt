package ru.smn.poker.game

import java.util.UUID

data class JoinGameRequest(val instanceName: String, val gameId: UUID)