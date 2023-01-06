package ru.smn.poker.combination

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ru.smn.poker.combination.data.Card
import ru.smn.poker.combination.data.Combination
import ru.smn.poker.combination.data.CombinationType
import kotlin.test.assertTrue


class CombinationComparerTest {

    private val combinationComparer = CombinationComparerImpl()

    @Test
    fun `should define high card`() {
        val winnerCombination = combinationComparer.compare(
            CombinationType.HIGH_CARD,
            listOf(
                Combination(
                    CombinationType.HIGH_CARD, listOf(
                        Card.A_C, Card.K_H, Card.TEN_D, Card.TWO_C, Card.THREE_C
                    )
                ),
                Combination(
                    CombinationType.HIGH_CARD, listOf(
                        Card.K_D, Card.Q_C, Card.TWO_D, Card.THREE_D, Card.SEVEN_C
                    )
                )
            )
        )

        assertTrue { winnerCombination.size == 1 }

        assertTrue {
            winnerCombination[0].cards.equals(
                listOf(
                    Card.A_C,
                    Card.K_H,
                    Card.TEN_D,
                    Card.TWO_C,
                    Card.THREE_C
                )
            )
        }
    }

    @Test
    @Disabled
    fun `should define one pair`() {
        val winnerCombination = combinationComparer.compare(
            CombinationType.ONE_PAIR,
            listOf(
                Combination(
                    CombinationType.ONE_PAIR, listOf(
                        Card.A_C, Card.A_D, Card.TEN_D, Card.TWO_C, Card.THREE_C
                    )
                ),
                Combination(
                    CombinationType.ONE_PAIR, listOf(
                        Card.K_D, Card.K_C, Card.TWO_D, Card.THREE_D, Card.SEVEN_C
                    )
                )
            )
        )

        assertTrue { winnerCombination.size == 1 }

        assertTrue {
            winnerCombination[0].cards.equals(
                listOf(
                    Card.A_C,
                    Card.A_D,
                    Card.TEN_D,
                    Card.TWO_C,
                    Card.THREE_C
                )
            )
        }
    }

}