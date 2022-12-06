package ru.smn.combination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.smn.combination.data.CardSizeData;
import ru.smn.combination.data.Combination;

import static ru.smn.combination.data.CombinationType.*;

public class ClassicCombinationGeneratorServiceTest {
    private final CombinationGeneratorService combinationGeneratorService = new ClassicCombinationGeneratorService();
    private final CombinationService combinationService = new ClassicCombinationService();

    @Test
    public void shouldGenerateFlushRoyalCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination flushRoyal = combinationGeneratorService.generate(FLUSH_ROYAL);
            final Combination combination = combinationService.findCombination(flushRoyal.getCards());
            Assertions.assertEquals(FLUSH_ROYAL, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, flushRoyal.getCards().size());
        }
    }

    @Test
    public void shouldGenerateStraightFlushCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination straightFlush = combinationGeneratorService.generate(STRAIGHT_FLUSH);
            final Combination combination = combinationService.findCombination(straightFlush.getCards());
            Assertions.assertEquals(STRAIGHT_FLUSH, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, straightFlush.getCards().size());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, straightFlush.getCards().size());
        }
    }

    @Test
    public void shouldGenerateKareCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination kare = combinationGeneratorService.generate(KARE);
            final Combination combination = combinationService.findCombination(kare.getCards());
            Assertions.assertEquals(KARE, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, kare.getCards().size());
        }
    }

    @Test
    public void shouldGenerateFullHouseCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination fullHouse = combinationGeneratorService.generate(FULL_HOUSE);
            final Combination combination = combinationService.findCombination(fullHouse.getCards());
            Assertions.assertEquals(FULL_HOUSE, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, fullHouse.getCards().size());
        }
    }

    @Test
    public void shouldGenerateOneFlushCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination flush = combinationGeneratorService.generate(FLUSH);
            final Combination combination = combinationService.findCombination(flush.getCards());
            Assertions.assertEquals(FLUSH, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, flush.getCards().size());
        }
    }

    @Test
    public void shouldGenerateStraightCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination straight = combinationGeneratorService.generate(STRAIGHT);
            final Combination combination = combinationService.findCombination(straight.getCards());
            Assertions.assertEquals(STRAIGHT, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, straight.getCards().size());
        }
    }

    @Test
    public void shouldGenerateThreeCardsCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination threeCards = combinationGeneratorService.generate(THREE_CARDS);
            final Combination combination = combinationService.findCombination(threeCards.getCards());
            Assertions.assertEquals(THREE_CARDS, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, threeCards.getCards().size());
        }
    }

    @Test
    public void shouldGenerateTwoPairCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination twoPair = combinationGeneratorService.generate(TWO_PAIR);
            final Combination combination = combinationService.findCombination(twoPair.getCards());
            Assertions.assertEquals(TWO_PAIR, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, twoPair.getCards().size());
        }
    }

    @Test
    public void shouldGenerateOnePairCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination onePair = combinationGeneratorService.generate(ONE_PAIR);
            final Combination combination = combinationService.findCombination(onePair.getCards());
            Assertions.assertEquals(ONE_PAIR, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, onePair.getCards().size());
        }
    }

    @Test
    @Disabled
    public void shouldGenerateHighCardCombination() {
        for (int i = 0; i < 1_000; i++) {
            final Combination highCard = combinationGeneratorService.generate(HIGH_CARD);
            final Combination combination = combinationService.findCombination(highCard.getCards());
            Assertions.assertEquals(HIGH_CARD, combination.getCombinationType());
            Assertions.assertEquals(CardSizeData.COMBINATION_SIZE, highCard.getCards().size());
        }
    }

}
