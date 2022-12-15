package ru.smn.poker.core

import ru.smn.poker.dto.Deal

interface DealHandler {
    fun handleDeal(deal: Deal)
}