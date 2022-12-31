package ru.smn.poker.core

import ru.smn.poker.dto.Instance

interface BankTimeManager {
    fun calculatingTime(instance: Instance, timeAction: Long)
}
