package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.game.CreateGameRequest
import ru.smn.poker.game.CreateGameResponse
import java.util.*

@Service
class GameServiceImpl : GameService {

    override fun createGame(createGameRequest: CreateGameRequest): CreateGameResponse {
        GameCore(UUID.randomUUID(), mutableListOf())
            .run {
                start()
            }
        return CreateGameResponse(true)
    }
}