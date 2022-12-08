package ru.smn.poker.core

import kotlinx.coroutines.Job
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GameStorage(
    val games: MutableMap<GameCore, Job?> = mutableMapOf()
) {
    fun getById(id: UUID): GameCore {
        return games.keys.first { game -> game.gameId == id }
    }

    fun addJob(game: GameCore, job: Job) {
        games[game] = job
    }

    fun add(game: GameCore) {
        games[game] = null
    }

    fun remove(id: UUID) {
        val game = games.keys.first { game ->
            game.gameId == id
        }
        games.remove(game)
    }
}