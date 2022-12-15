package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.combination.CombinationService
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.printC

@Service
class DealHandlerImpl(private val combinationService: CombinationService) : DealHandler {
    override fun handle(instances: MutableList<Instance>, deal: Deal) {
        instances.forEach { instance ->
            val allCards = listOfNotNull(
                deal.flop1, deal.flop2, deal.flop3, deal.tern, deal.river
            ) + instance.cards

            val foundCombination = combinationService.findCombination(allCards)
            printC("instance: ${instance.name}, combination: $foundCombination")
        }
    }
}