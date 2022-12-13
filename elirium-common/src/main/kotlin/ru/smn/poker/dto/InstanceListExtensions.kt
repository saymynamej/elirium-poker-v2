package ru.smn.poker.dto

import ru.smn.poker.actions.ActionType
import ru.smn.poker.actions.NoAction
import ru.smn.poker.actions.Role

private const val NOT_FOUND_INDEX: Int = -1

fun MutableList<Instance>.setUp(stage: Stage): MutableList<Instance> {
    return this.removeFolded()
        .sortByStage(stage)
        .also {
            if (!this.everyoneInAllIn()) {
                this.forEach { instance -> instance.action = NoAction() }
            }
        }.toMutableList()
}

fun MutableList<Instance>.isOnePlayerLeft(): Boolean {
    return this.count { instance ->
        instance.action.type != ActionType.FOLD
    } == 1
}

fun MutableList<Instance>.everyoneInAllIn(): Boolean {
    return this.removeFolded().all { instance -> instance.action.type == ActionType.ALL_IN }
}


fun MutableList<Instance>.isStageNotFinished(stage: Stage): Boolean {
    return !this.everyoneHasTheSameBet(stage) && !this.everyoneInAllIn()
}

fun MutableList<Instance>.everyoneHasTheSameBet(stage: Stage): Boolean {
    val bets = this.removeFolded().map { instance ->
        instance.history[stage]
    }.map { actions ->
        actions!!.sumOf { action -> action.count() }
    }
    val distinct = bets.distinct()
    return distinct.size == 1 && distinct[0] != 0L
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
    this.filter { instance -> instance.action.type != ActionType.FOLD }
        .toMutableList()

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
