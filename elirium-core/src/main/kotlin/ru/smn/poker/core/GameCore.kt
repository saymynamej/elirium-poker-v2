package ru.smn.poker.core

import ru.smn.combination.CardContainer
import ru.smn.poker.*
import ru.smn.poker.dto.Deal
import ru.smn.poker.dto.Instance
import ru.smn.poker.log.EliriumLogger
import java.util.*


class GameCore(
    private val active: Boolean = true,
    private val gameHandler: GameHandler,
    val gameId: UUID,
    val instances: MutableList<Instance>,
    var deal: Deal = Deal(gameId),
) {
    fun start() {
        //Цикл игры
        while (active) {
            /* Цикл Игры
               * */
            this.deal = buildDeal(instances)
            while (true) {
                val stage = deal.stage.type
                EliriumLogger("stage: $stage is started")

                val sortedInstances = instances.distributeRoles()
                    .sortByStage(stage)

                if (instances.everyoneInAllIn()) {
                    continue
                }
                if (instances.isOnePlayerLeft()) {
                    break
                }
                /* Цикл Раздачи. Условия для выхода.
                   * 1. Все в олл ине
                   * 2. Остался один игрок
                   * */
                while (true) {
                    if (instances.everyoneHasTheSameBet(stage)) {
                        break
                    }
                    if (instances.everyoneInAllIn()) {
                        continue
                    }
                    if (instances.isOnePlayerLeft()) {
                        break
                    }
                    /* Цикл круга. Условия для выхода.
                    * 1. Все кто не сфолдил уровнялись.
                    * 2. Остался один игрок
                    * 3. Все в олл ине
                    * */
                    sortedInstances.removeFolded().forEach { instance ->
                        gameHandler.waitAndHandle(deal, instance.action, instance)
                    }
                }
            }
        }
    }

    fun addInstance(instance: Instance) {
        instances.add(instance)
    }

    private fun buildDeal(instances: MutableList<Instance>): Deal {
        val cardContainer = CardContainer()
        return Deal(
            gameId = gameId,
            flop1 = cardContainer.retrieveRandomCard(),
            flop2 = cardContainer.retrieveRandomCard(),
            flop3 = cardContainer.retrieveRandomCard(),
            tern = cardContainer.retrieveRandomCard(),
            river = cardContainer.retrieveRandomCard()
        )
    }


}
