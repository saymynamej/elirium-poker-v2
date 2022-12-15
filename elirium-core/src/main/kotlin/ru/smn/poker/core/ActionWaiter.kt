package ru.smn.poker.core

import ru.smn.poker.dto.Instance

interface ActionWaiter {
    fun wait(instance: Instance)
}