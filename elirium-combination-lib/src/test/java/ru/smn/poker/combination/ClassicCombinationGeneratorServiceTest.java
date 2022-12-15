package ru.smn.poker.combination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.smn.poker.combination.data.CardSizeData;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

public class ClassicCombinationGeneratorServiceTest {
    private final CombinationGeneratorService combinationGeneratorService = new ClassicCombinationGeneratorService();
    private final CombinationService combinationService = new ClassicCombinationService();

    @Test
    public void shouldGenerateFlushRoyalCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination flushRoyal = combinationGeneratorService.generate(CombinationType.FLUSH_ROYAL);
            final Combination combination = combinationService.findCombination(flushRoyal.getCards());
            Assertions.assertEquals(CombinationType.FLUSH_ROYAL, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, flushRoyal.getCards().size());
        }
    }

    @Test
    public void shouldGenerateStraightFlushCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination straightFlush = combinationGeneratorService.generate(CombinationType.STRAIGHT_FLUSH);
            final Combination combination = combinationService.findCombination(straightFlush.getCards());
            Assertions.assertEquals(CombinationType.STRAIGHT_FLUSH, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, straightFlush.getCards().size());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, straightFlush.getCards().size());
        }
    }

    @Test
    public void shouldGenerateQuadsCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination quads = combinationGeneratorService.generate(CombinationType.QUADS);
            final Combination combination = combinationService.findCombination(quads.getCards());
            Assertions.assertEquals(CombinationType.QUADS, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, quads.getCards().size());
        }
    }

    @Test
    public void shouldGenerateFullHouseCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination fullHouse = combinationGeneratorService.generate(CombinationType.FULL_HOUSE);
            final Combination combination = combinationService.findCombination(fullHouse.getCards());
            Assertions.assertEquals(CombinationType.FULL_HOUSE, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, fullHouse.getCards().size());
        }
    }

    @Test
    public void shouldGenerateOneFlushCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination flush = combinationGeneratorService.generate(CombinationType.FLUSH);
            final Combination combination = combinationService.findCombination(flush.getCards());
            Assertions.assertEquals(CombinationType.FLUSH, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, flush.getCards().size());
        }
    }

    @Test
    public void shouldGenerateStraightCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination straight = combinationGeneratorService.generate(CombinationType.STRAIGHT);
            final Combination combination = combinationService.findCombination(straight.getCards());
            Assertions.assertEquals(CombinationType.STRAIGHT, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, straight.getCards().size());
        }
    }

    @Test
    public void shouldGenerateThreeCardsCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination threeCards = combinationGeneratorService.generate(CombinationType.THREE_CARDS);
            final Combination combination = combinationService.findCombination(threeCards.getCards());
            Assertions.assertEquals(CombinationType.THREE_CARDS, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, threeCards.getCards().size());
        }
    }

    @Test
    public void shouldGenerateTwoPairCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination twoPair = combinationGeneratorService.generate(CombinationType.TWO_PAIR);
            final Combination combination = combinationService.findCombination(twoPair.getCards());
            Assertions.assertEquals(CombinationType.TWO_PAIR, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, twoPair.getCards().size());
        }
    }

    @Test
    public void shouldGenerateOnePairCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination onePair = combinationGeneratorService.generate(CombinationType.ONE_PAIR);
            final Combination combination = combinationService.findCombination(onePair.getCards());
            Assertions.assertEquals(CombinationType.ONE_PAIR, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, onePair.getCards().size());
        }
    }

    @Test
    @Disabled
    public void shouldGenerateHighCardCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination highCard = combinationGeneratorService.generate(CombinationType.HIGH_CARD);
            final Combination combination = combinationService.findCombination(highCard.getCards());
            Assertions.assertEquals(CombinationType.HIGH_CARD, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, highCard.getCards().size());
        }
    }

}
