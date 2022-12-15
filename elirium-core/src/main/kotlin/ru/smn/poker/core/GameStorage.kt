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

    fun addJob(gameImpl: Game, job: Job) {
        games[gameImpl] = job
    }

    fun add(gameImpl: GameImpl) {
        games[gameImpl] = null
    }

    fun remove(id: UUID) {
        val game = games.keys.first { game ->
            game.gameId == id
        }
        games.remove(game)
    }
}