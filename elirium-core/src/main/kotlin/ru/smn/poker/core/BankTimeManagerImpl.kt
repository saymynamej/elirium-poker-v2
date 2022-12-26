package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.dto.Instance

@Service
class BankTimeManagerImpl : BankTimeManager {
    override fun calculatingTime(instance: Instance, startTime: Long) {
        val endTime = System.currentTimeMillis()

        instance.timeBank -= ((endTime - startTime) / 1000).toInt()
        if (instance.timeBank < 0) {
            instance.timeBank = 0
        }
    }
}
