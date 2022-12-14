package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.combination.CardContainer
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage
import ru.smn.poker.dto.StageStatus
import java.util.*


@Service
class GameSetup(
    val actionHandler: ActionHandler,
) {
    fun setUp(gameId: UUID, deal: Deal, instances: MutableList<Instance>): Deal {
        val cardContainer = CardContainer()
        actionHandler.handleBlinds(deal, instances)
        instances.forEach { instance ->
            instance.cards = mutableListOf(
                cardContainer.retrieveRandomCard(),
                cardContainer.retrieveRandomCard()
            )
        }
        deal.apply {
            flop1 = cardContainer.retrieveRandomCard()
            flop2 = cardContainer.retrieveRandomCard()
            flop3 = cardContainer.retrieveRandomCard()
            tern = cardContainer.retrieveRandomCard()
            river = cardContainer.retrieveRandomCard()
            stage = StageStatus(Stage.PRE_FLOP, false)
            finished = false
            bank = 0
        }
        return deal
    }

}