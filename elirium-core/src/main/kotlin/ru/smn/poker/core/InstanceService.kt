package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.dto.Instance
import java.util.*


@Service
class InstanceService(val gameStorage: GameStorage) {

    fun findInstance(name: String): Instance {
        return gameStorage.games
            .flatMap { game -> game.instances }
            .first { instance -> instance.instanceName == name }
    }

    fun addInstance(gameId: UUID, instance: Instance) {
        gameStorage.games
            .first { game -> game.gameId == gameId }
            .addInstance(instance)
    }
}