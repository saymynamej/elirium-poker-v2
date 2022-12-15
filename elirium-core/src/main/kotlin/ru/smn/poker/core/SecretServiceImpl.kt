package ru.smn.poker.core

import org.springframework.stereotype.Service
import ru.smn.poker.actions.ActionResponse
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.dto.Stage

@Service
class SecretServiceImpl : SecretService {
    override fun secretActionResponse(
        targetInstanceName: String,
        actionResponse: ActionResponse,
    ): ActionResponse {
        return ActionResponse(
            actionResponse.count,
            actionResponse.gameId,
            actionResponse.playerName,
            secretDeal(actionResponse.deal),
            secretInstances(targetInstanceName, actionResponse.instances)
        )
    }

    private fun secretInstances(
        targetInstanceName: String,
        instances: List<Instance>,
    ): List<Instance> {
        val copyInstances = instances.toMutableList()
        copyInstances.forEach { instance ->
            if (instance.name != targetInstanceName) {
                instance.cards.clear()
                instance.history.forEach { (_, bets) -> bets.clear() }
            }
        }
        return copyInstances
    }

    private fun secretDeal(deal: Deal): Deal {
        val secretDeal = Deal(deal.gameId)

        secretDeal.finished = deal.finished
        secretDeal.stage = deal.stage
        secretDeal.bigBlindBet = deal.bigBlindBet
        secretDeal.smallBlindBet = deal.smallBlindBet
        secretDeal.bank = deal.bank

        when (deal.stage.type) {
            Stage.PRE_FLOP -> {}
            Stage.FLOP -> {
                secretDeal.flop1 = deal.flop1
                secretDeal.flop2 = deal.flop2
                secretDeal.flop3 = deal.flop3
            }

            Stage.TERN -> secretDeal.tern = deal.tern
            Stage.RIVER -> secretDeal.river = deal.river
        }


        return secretDeal
    }
}