package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.dto.Instance
import java.util.*


@Service
class InstanceService(val gameStorage: GameStorage) {

    fun addInstance(gameId: UUID, instance: Instance) {
        gameStorage.getById(gameId)
            .addInstance(instance)
    }
}