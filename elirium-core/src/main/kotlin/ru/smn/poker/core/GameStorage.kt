package ru.smn.poker.core

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GameStorage(
    val games: MutableList<GameCore>
) {

    fun add(gameCore: GameCore) {
        games.add(gameCore)
    }

    fun remove(id: UUID) {
        games.remove(games.first { game -> game.gameId == id })
    }
}