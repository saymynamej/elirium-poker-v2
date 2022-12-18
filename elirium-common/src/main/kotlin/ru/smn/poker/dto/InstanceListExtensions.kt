package ru.smn.poker.dto

import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.CountAction
import ru.smn.poker.actions.Role

private const val NOT_FOUND_INDEX: Int = -1
private const val MAXIMUM_RETRIES_FOR_FINDING_PLAYER: Int = 18

data class IndexAndInstance(val index: Int, val instance: Instance)

fun MutableList<Instance>.isDealFinished(stage: Stage): Boolean {
    return stage == Stage.RIVER || this.isOnePlayerLeft()
}

fun MutableList<Instance>.isOnePlayerLeft(): Boolean {
    return this.removeFolded().size == 1
}

fun MutableList<Instance>.nextInstanceFunction(): () -> Instance {
    var currentIndex = 0
    val instanceForAction: () -> Instance = {
        val (index, instance) = tryToGetNextElement(this, currentIndex, 0)
        currentIndex = index
        instance
    }
    return instanceForAction
}

fun tryToGetNextElement(instances: MutableList<Instance>, currentIndex: Int, invokedTimes: Int): IndexAndInstance {
    if (invokedTimes > MAXIMUM_RETRIES_FOR_FINDING_PLAYER) {
        throw RuntimeException("cannot retrieve next player")
    }
    var copyCurrentIndex = currentIndex.let { index ->
        if (index >= instances.size) {
            0
        } else index
    }
    val instance = instances[copyCurrentIndex]
    if (instance.lastActionIs(ActionType.FOLD)) {
        return tryToGetNextElement(instances, ++copyCurrentIndex, invokedTimes.inc())
    }
    return IndexAndInstance(++copyCurrentIndex, instance)
}

fun MutableList<Instance>.allChecks(stage: Stage): Boolean {
    return this.removeFolded()
        .all { instance -> instance.lastActionIs(stage, ActionType.CHECK) }
}

fun MutableList<Instance>.everyoneInAllIn(): Boolean {
    return this.removeFolded().all { instance -> instance.lastActionIs(ActionType.ALL_IN) }
}


fun MutableList<Instance>.isStageFinished(stage: Stage): Boolean {
    return allChecks(stage) || everyoneHasTheSameBet(stage) || everyoneInAllIn() || isOnePlayerLeft()
}

fun MutableList<Instance>.isStageNotFinished(stage: Stage): Boolean {
    return !isStageFinished(stage)
}

fun MutableList<Instance>.everyoneHasTheSameBet(stage: Stage): Boolean {
    val removedFoldedInstances = this.removeFolded()

    val bets = removedFoldedInstances
        .map { instance -> instance.history[stage] }
        .map { actions ->
            actions!!
                .filterIsInstance<CountAction>()
                .sumOf { action -> action.count }
        }

    val bigBlindHasOneBet = stage == Stage.PRE_FLOP && removedFoldedInstances
        .filter { instance -> instance.role == Role.BIG_BLIND }
        .filter { instance -> instance.history[Stage.PRE_FLOP] != null }
        .map { instance -> instance.history[Stage.PRE_FLOP]!!.size == 1 }
        .firstOrNull() ?: false

    val distinct = bets.distinct()
    return distinct.size == 1 && distinct[0] != 0L && !bigBlindHasOneBet
}

fun MutableList<Instance>.getNextIndexForRole(role: Role): Int {
    return this.indexOfFirst { instance -> instance.role == role }.run {
        if (this == lastIndex) return@run 0
        if (this == NOT_FOUND_INDEX) return NOT_FOUND_INDEX
        return@run this.inc()
    }
}

fun MutableList<Instance>.anyHasRoles(vararg role: Role): Boolean {
    return this.map { instance -> instance.role }
        .any { pos -> role.contains(pos) }
}

fun MutableList<Instance>.allHasRole(role: Role): Boolean {
    return this.map { instance -> instance.role }
        .any { pos -> role == pos }
}

fun MutableList<Instance>.removeFolded(): MutableList<Instance> =
    this.filter { instance -> !instance.lastActionIs(ActionType.FOLD) }.toMutableList()

fun MutableList<Instance>.distributeRoles(): MutableList<Instance> {
    if (this.isEmpty() || this.size < 2) throw RuntimeException("list is not enough size")

    if (allHasRole(Role.EMPTY)) {
        this.forEachIndexed { index, instance ->
            instance.role = Role.findByGrade(index)
        }
        return this
    }

    Role.values()
        .map { role -> role to getNextIndexForRole(role) }
        .filter { it.second != NOT_FOUND_INDEX }
        .forEach { this[it.second].role = it.first }

    return this
}

fun MutableList<Instance>.sortByStage(stage: Stage): MutableList<Instance> {
    if (stage == Stage.PRE_FLOP) return sortBeforeFlop()
    return sortAfterFlop()
}

fun MutableList<Instance>.sortAfterFlop(): MutableList<Instance> {
    return this.sortedBy { instance ->
        instance.role.afterFlopGrade
    }.toMutableList()
}

fun MutableList<Instance>.sortBeforeFlop(): MutableList<Instance> {
    return this.sortedBy { instance ->
        instance.role.beforeFlopGrade
    }.toMutableList()
}