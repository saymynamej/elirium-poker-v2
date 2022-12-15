package ru.smn.poker.core

import ru.smn.poker.dto.Instance

interface Game {
    suspend fun start()
    suspend fun stop()
    fun addInstance(instance: Instance)

    fun removeInstance(instance: Instance)
}