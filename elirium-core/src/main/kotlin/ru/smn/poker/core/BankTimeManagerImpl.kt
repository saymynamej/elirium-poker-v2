package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.dto.Instance

@Service
class BankTimeManagerImpl : BankTimeManager {
    val DEFAULT_TIME_TO_ACTION: Int = 20
    override fun calculatingTime(instance: Instance, timeAction: Long) {
        val waitingTime: Int = (timeAction / 1000).toInt()
        println(waitingTime)
        if (waitingTime <= DEFAULT_TIME_TO_ACTION) {
            println(waitingTime)
            return
        }
        instance.timeBank -= (waitingTime - DEFAULT_TIME_TO_ACTION)
    }
}
