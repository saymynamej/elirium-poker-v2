package ru.smn.poker.core

import kotlinx.coroutines.Job
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameStorage(
    val games: MutableMap<Game, Job?> = mutableMapOf(),
) {
    fun getById(id: UUID): Game {
        return games.keys.first { game -> game.gameId == id }
    }

    fun addJob(game: Game, job: Job) {
        games[game] = job
    }

    fun add(game: Game) {
        games[game] = null
    }

    fun remove(id: UUID) {
        val game = games.keys.first { game ->
            game.gameId == id
        }
        games.remove(game)
    }
}