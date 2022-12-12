package ru.smn.poker

import org.awaitility.Awaitility
import ru.smn.poker.dto.Instance
import java.util.concurrent.TimeUnit

fun Awaitility.waitUntil(predicate: () -> Boolean) {
    Awaitility.await()
        .atMost(222, TimeUnit.SECONDS)
        .until { predicate.invoke() }
}

fun Awaitility.waitActive(instance: Instance) {
    Awaitility.await()
        .atMost(222, TimeUnit.SECONDS)
        .until { instance.active }
}