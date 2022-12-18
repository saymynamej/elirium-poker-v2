package ru.smn.poker.exception

class ForbiddenActionException(override val message: String) : RuntimeException(message)