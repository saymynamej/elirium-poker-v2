package ru.smn.combination;

import org.junit.jupiter.api.Test;
import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.smn.combination.data.CardType.*;

public class ClassicCombinationServiceTest {

    private static final List<CardType> FLUSH_ROYAL_HEART_FULL = new ArrayList<>(Arrays.asList(A_H, K_H, Q_H, J_H, TEN_H, FOUR_H, K_S));
    private static final List<CardType> FLUSH_ROYAL_HEART_COMBINATION = new ArrayList<>(Arrays.asList(A_H, K_H, Q_H, J_H, TEN_H));
    private static final List<CardType> FLUSH_ROYAL_SPADE_FULL = new ArrayList<>(Arrays.asList(FOUR_H, TWO_H, A_S, K_S, Q_S, J_S, TEN_S));
    private static final List<CardType> FLUSH_ROYAL_SPADE_COMBINATION = new ArrayList<>(Arrays.asList(A_S, K_S, Q_S, J_S, TEN_S));
    private static final List<CardType> FLUSH_ROYAL_DIAMOND_FULL = new ArrayList<>(Arrays.asList(TWO_C, TEN_D, A_D, K_D, Q_D, J_D, SEVEN_C));
    private static final List<CardType> FLUSH_ROYAL_DIAMOND_COMBINATION = new ArrayList<>(Arrays.asList(A_D, K_D, Q_D, J_D, TEN_D));
    private static final List<CardType> FLUSH_ROYAL_CLUB_FULL = new ArrayList<>(Arrays.asList(A_H, K_H, TEN_C, J_C, Q_C, K_C, A_C));
    private static final List<CardType> FLUSH_ROYAL_CLUB_COMBINATION = new ArrayList<>(Arrays.asList(A_C, K_C, Q_C, J_C, TEN_C));
    private static final List<CardType> STRAIT_FLUSH_CLUB_FULL = new ArrayList<>(Arrays.asList(EIGHT_C, SEVEN_C, TEN_C, J_C, NINE_C, FOUR_H, K_S));
    private static final List<CardType> STRAIT_FLUSH_CLUB_COMBINATION = new ArrayList<>(Arrays.asList(J_C, TEN_C, NINE_C, EIGHT_C, SEVEN_C));
    private static final List<CardType> STRAIT_FLUSH_HEART_FULL = new ArrayList<>(Arrays.asList(TWO_H, FOUR_D, EIGHT_H, NINE_H, TEN_H, J_H, Q_H));
    private static final List<CardType> STRAIT_FLUSH_HEART_COMBINATION = new ArrayList<>(Arrays.asList(Q_H, J_H, TEN_H, NINE_H, EIGHT_H));
    private static final List<CardType> STRAIT_FLUSH_DIAMOND_FULL = new ArrayList<>(Arrays.asList(TWO_H, THREE_D, FOUR_D, FIVE_D, SIX_D, SEVEN_D, Q_H));
    private static final List<CardType> STRAIT_FLUSH_DIAMOND_COMBINATION = new ArrayList<>(Arrays.asList(SEVEN_D, SIX_D, FIVE_D, FOUR_D, THREE_D));
    private static final List<CardType> STRAIT_FLUSH_SPADE_FULL = new ArrayList<>(Arrays.asList(TWO_S, THREE_S, FOUR_S, FIVE_S, SIX_S, SEVEN_S, EIGHT_S));
    private static final List<CardType> STRAIT_FLUSH_SPADE_COMBINATION = new ArrayList<>(Arrays.asList(EIGHT_S, SEVEN_S, SIX_S, FIVE_S, FOUR_S));
    private static final List<CardType> STRAIT_FLUSH_FULL = new ArrayList<>(Arrays.asList(TWO_H, SEVEN_C, EIGHT_C, NINE_C, TEN_C, J_C, K_H));
    private static final List<CardType> STRAIT_FLUSH_COMBINATION = new ArrayList<>(Arrays.asList(J_C, TEN_C, NINE_C, EIGHT_C, SEVEN_C));
    private static final List<CardType> STRAIT_FLUSH_FULL_2 = new ArrayList<>(Arrays.asList(EIGHT_S, NINE_S, Q_S, TWO_D, THREE_C, J_S, TEN_S));
    private static final List<CardType> STRAIT_FLUSH_COMBINATION_2 = new ArrayList<>(Arrays.asList(Q_S, J_S, TEN_S, NINE_S, EIGHT_S));
    private static final List<CardType> STRAIT_FLUSH_WITH_ACE_FULL = new ArrayList<>(Arrays.asList(A_H, TWO_H, THREE_H, FOUR_H, FIVE_H, J_C, K_H));
    private static final List<CardType> STRAIT_FLUSH_WITH_ACE_COMBINATION = new ArrayList<>(Arrays.asList(A_H, FIVE_H, FOUR_H, THREE_H, TWO_H));
    private static final List<CardType> KARE_FULL = new ArrayList<>(Arrays.asList(NINE_C, FOUR_H, K_S, SIX_C, SIX_H, SIX_S, SIX_D));
    private static final List<CardType> KARE_COMBINATION = new ArrayList<>(Arrays.asList(K_S, SIX_C, SIX_H, SIX_S, SIX_D));
    private static final List<CardType> KARE_FULL2 = new ArrayList<>(Arrays.asList(A_C, A_D, A_S, A_H, TEN_C, SIX_D, K_H));
    private static final List<CardType> KARE_COMBINATION2 = new ArrayList<>(Arrays.asList(A_C, A_D, A_S, A_H, K_H));
    private static final List<CardType> KARE_FULL3 = new ArrayList<>(Arrays.asList(Q_C, FOUR_H, NINE_C, NINE_H, NINE_D, NINE_S, SIX_D));
    private static final List<CardType> KARE_COMBINATION3 = new ArrayList<>(Arrays.asList(Q_C, NINE_C, NINE_H, NINE_D, NINE_S));
    private static final List<CardType> KARE_FULL4 = new ArrayList<>(Arrays.asList(SEVEN_S, FOUR_H, SEVEN_C, SIX_C, SEVEN_D, SEVEN_H, A_D));
    private static final List<CardType> KARE_COMBINATION4 = new ArrayList<>(Arrays.asList(A_D, SEVEN_S, SEVEN_C, SEVEN_D, SEVEN_H));
    private static final List<CardType> FLUSH_HEART_FULL = new ArrayList<>(Arrays.asList(NINE_H, A_H, K_H, TWO_H, FIVE_H, FOUR_H, K_S));
    private static final List<CardType> FLUSH_HEART_COMBINATION = new ArrayList<>(Arrays.asList(A_H, K_H, NINE_H, FIVE_H, FOUR_H));
    private static final List<CardType> FLUSH_DIAMOND_FULL = new ArrayList<>(Arrays.asList(J_D, TWO_D, THREE_D, SEVEN_D, A_H, A_C, K_D));
    private static final List<CardType> FLUSH_DIAMOND_COMBINATION = new ArrayList<>(Arrays.asList(K_D, J_D, SEVEN_D, THREE_D, TWO_D));
    private static final List<CardType> FLUSH_SPADE_FULL = new ArrayList<>(Arrays.asList(K_S, Q_S, SEVEN_S, FOUR_S, FIVE_S, K_H, K_C));
    private static final List<CardType> FLUSH_SPADE_COMBINATION = new ArrayList<>(Arrays.asList(K_S, Q_S, SEVEN_S, FIVE_S, FOUR_S));
    private static final List<CardType> FLUSH_CLUB_FULL = new ArrayList<>(Arrays.asList(A_C, K_C, J_C, Q_C, NINE_C, TWO_C, THREE_C));
    private static final List<CardType> FLUSH_CLUB_COMBINATION = new ArrayList<>(Arrays.asList(A_C, K_C, Q_C, J_C, NINE_C));
    private static final List<CardType> FULL_HOUSE_FULL = new ArrayList<>(Arrays.asList(EIGHT_C, EIGHT_D, EIGHT_H, FIVE_D, FIVE_C, FIVE_H, FOUR_H));
    private static final List<CardType> FULL_HOUSE_COMBINATION = new ArrayList<>(Arrays.asList(EIGHT_C, EIGHT_D, EIGHT_H, FIVE_D, FIVE_C));
    private static final List<CardType> FULL_HOUSE_FULL2 = new ArrayList<>(Arrays.asList(A_D, A_C, SEVEN_D, A_H, K_C, K_H, FOUR_H));
    private static final List<CardType> FULL_HOUSE_COMBINATION2 = new ArrayList<>(Arrays.asList(A_D, A_C, A_H, K_C, K_H));
    private static final List<CardType> FULL_HOUSE_FULL3 = new ArrayList<>(Arrays.asList(EIGHT_C, EIGHT_D, Q_C, Q_H, Q_D, TEN_C, TEN_D));
    private static final List<CardType> FULL_HOUSE_COMBINATION3 = new ArrayList<>(Arrays.asList(Q_C, Q_H, Q_D, TEN_C, TEN_D));
    private static final List<CardType> FULL_HOUSE_FULL4 = new ArrayList<>(Arrays.asList(J_D, EIGHT_D, SEVEN_H, SEVEN_D, FIVE_C, J_C, J_H));
    private static final List<CardType> FULL_HOUSE_COMBINATION4 = new ArrayList<>(Arrays.asList(J_D, J_C, J_H, SEVEN_H, SEVEN_D));
    private static final List<CardType> STRAIT_FULL = new ArrayList<>(Arrays.asList(EIGHT_C, SEVEN_S, SIX_H, FIVE_D, THREE_D, FOUR_H, TWO_D));
    private static final List<CardType> STRAIT_COMBINATION = new ArrayList<>(Arrays.asList(EIGHT_C, SEVEN_S, SIX_H, FIVE_D, FOUR_H));
    private static final List<CardType> STRAIT_FULL_2 = new ArrayList<>(Arrays.asList(THREE_H, FIVE_C, SEVEN_C, EIGHT_C, NINE_D, TEN_H, J_H));
    private static final List<CardType> STRAIT_COMBINATION_2 = new ArrayList<>(Arrays.asList(J_H, TEN_H, NINE_D, EIGHT_C, SEVEN_C));
    private static final List<CardType> STRAIT_FULL_3 = new ArrayList<>(Arrays.asList(A_D, J_C, TEN_C, Q_C, K_C, J_H, J_D));
    private static final List<CardType> STRAIT_COMBINATION_3 = new ArrayList<>(Arrays.asList(A_D, K_C, Q_C, J_C, TEN_C));
    private static final List<CardType> STRAIT_FULL_4 = new ArrayList<>(Arrays.asList(SEVEN_C, EIGHT_C, NINE_C, TEN_H, J_H, Q_H, K_D));
    private static final List<CardType> STRAIT_COMBINATION_4 = new ArrayList<>(Arrays.asList(K_D, Q_H, J_H, TEN_H, NINE_C));
    private static final List<CardType> STRAIT_FULL_5 = new ArrayList<>(Arrays.asList(SEVEN_C, EIGHT_C, NINE_C, TEN_H, J_H, A_H, K_H));
    private static final List<CardType> STRAIT_COMBINATION_5 = new ArrayList<>(Arrays.asList(J_H, TEN_H, NINE_C, EIGHT_C, SEVEN_C));
    private static final List<CardType> STRAIT_FULL_6 = new ArrayList<>(Arrays.asList(TWO_H, SEVEN_C, EIGHT_C, NINE_C, TEN_H, J_H, K_H));
    private static final List<CardType> STRAIT_COMBINATION_6 = new ArrayList<>(Arrays.asList(J_H, TEN_H, NINE_C, EIGHT_C, SEVEN_C));
    private static final List<CardType> STRAIT_FULL_7 = new ArrayList<>(Arrays.asList(TWO_H, THREE_C, FOUR_S, FIVE_H, SIX_C, J_H, K_H));
    private static final List<CardType> STRAIT_COMBINATION_7 = new ArrayList<>(Arrays.asList(SIX_C, FIVE_H, FOUR_S, THREE_C, TWO_H));
    private static final List<CardType> STRAIT_FULL_8 = new ArrayList<>(Arrays.asList(A_C, TWO_C, THREE_C, FOUR_D, FIVE_D, J_H, K_H));
    private static final List<CardType> STRAIT_COMBINATION_8 = new ArrayList<>(Arrays.asList(A_C, FIVE_D, FOUR_D, THREE_C, TWO_C));
    private static final List<CardType> TWO_PAIR_FULL = new ArrayList<>(Arrays.asList(A_H, A_D, FOUR_H, FOUR_C, SEVEN_S, SEVEN_C, K_D));
    private static final List<CardType> TWO_PAIR_COMBINATION = new ArrayList<>(Arrays.asList(A_H, A_D, K_D, SEVEN_S, SEVEN_C));
    private static final List<CardType> THREE_FULL = new ArrayList<>(Arrays.asList(THREE_H, THREE_C, THREE_S, K_D, SEVEN_S, FOUR_H, NINE_C));
    private static final List<CardType> THREE_COMBINATION = new ArrayList<>(Arrays.asList(K_D, NINE_C, THREE_H, THREE_C, THREE_S));
    private static final List<CardType> THREE_FULL_2 = new ArrayList<>(Arrays.asList(SEVEN_S, FOUR_H, NINE_C, A_D, A_C, A_H, K_D));
    private static final List<CardType> THREE_COMBINATION_2 = new ArrayList<>(Arrays.asList(A_D, A_C, A_H, K_D, NINE_C));
    private static final List<CardType> THREE_FULL_3 = new ArrayList<>(Arrays.asList(SEVEN_S, FOUR_H, A_D, NINE_C, A_C, A_H, K_D));
    private static final List<CardType> THREE_COMBINATION_3 = new ArrayList<>(Arrays.asList(A_D, A_C, A_H, K_D, NINE_C));
    private static final List<CardType> PAIR_FULL = new ArrayList<>(Arrays.asList(THREE_H, THREE_C, A_C, K_S, TWO_D, FOUR_D, SEVEN_S));
    private static final List<CardType> PAIR_FULL_COMBINATION = new ArrayList<>(Arrays.asList(A_C, K_S, SEVEN_S, THREE_H, THREE_C));
    private static final List<CardType> HIGH_CARD_FULL = new ArrayList<>(Arrays.asList(TWO_H, TEN_C, A_H, K_D, SEVEN_S, FOUR_H, NINE_C));
    private static final List<CardType> HIGH_COMBINATION = new ArrayList<>(Arrays.asList(A_H, K_D, TEN_C, NINE_C, SEVEN_S));
    private static final List<CardType> HIGH_CARD_FULL_2 = new ArrayList<>(Arrays.asList(A_C, TEN_C, FIVE_H, K_D, EIGHT_H, FOUR_H, NINE_C));
    private static final List<CardType> HIGH_COMBINATION_2 = new ArrayList<>(Arrays.asList(A_C, K_D, TEN_C, NINE_C, EIGHT_H));
    private final ClassicCombinationService checkSimpleCombinationService = new ClassicCombinationService();

    @Test
    public void testHeardFlushRoyal() {
        final Combination flashRoyal = checkSimpleCombinationService.findCombination(FLUSH_ROYAL_HEART_FULL);
        assertEquals(CombinationType.FLUSH_ROYAL, flashRoyal.getCombinationType());
        assertEquals(FLUSH_ROYAL_HEART_COMBINATION, flashRoyal.getCards());
    }

    @Test
    public void testSpadeFlushRoyal() {
        final Combination flashRoyal = checkSimpleCombinationService.findCombination(FLUSH_ROYAL_SPADE_FULL);
        assertEquals(CombinationType.FLUSH_ROYAL, flashRoyal.getCombinationType());
        assertEquals(FLUSH_ROYAL_SPADE_COMBINATION, flashRoyal.getCards());
    }

    @Test
    public void testDiamondFlushRoyal() {
        final Combination flashRoyal = checkSimpleCombinationService.findCombination(FLUSH_ROYAL_DIAMOND_FULL);
        assertEquals(CombinationType.FLUSH_ROYAL, flashRoyal.getCombinationType());
        assertEquals(FLUSH_ROYAL_DIAMOND_COMBINATION, flashRoyal.getCards());
    }

    @Test
    public void testClubFlushRoyal() {
        final Combination flashRoyal = checkSimpleCombinationService.findCombination(FLUSH_ROYAL_CLUB_FULL);
        assertEquals(CombinationType.FLUSH_ROYAL, flashRoyal.getCombinationType());
        assertEquals(FLUSH_ROYAL_CLUB_COMBINATION, flashRoyal.getCards());
    }

    @Test
    public void testStraitFlush() {
        final Combination straitFlush = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_FULL);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlush.getCombinationType());
        assertEquals(STRAIT_FLUSH_COMBINATION, straitFlush.getCards());
    }

    @Test
    public void testStraitFlush2() {
        final Combination straitFlush = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_FULL_2);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlush.getCombinationType());
        assertEquals(STRAIT_FLUSH_COMBINATION_2, straitFlush.getCards());
    }

    @Test
    public void testClubStraitFlush() {
        final Combination straitFlush = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_CLUB_FULL);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlush.getCombinationType());
        assertEquals(STRAIT_FLUSH_CLUB_COMBINATION, straitFlush.getCards());
    }

    @Test
    public void testHeartStraitFlush() {
        final Combination straitFlush2 = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_HEART_FULL);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlush2.getCombinationType());
        assertEquals(STRAIT_FLUSH_HEART_COMBINATION, straitFlush2.getCards());
    }

    @Test
    public void testDiamondStraitFlush() {
        final Combination straitFlush2 = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_DIAMOND_FULL);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlush2.getCombinationType());
        assertEquals(STRAIT_FLUSH_DIAMOND_COMBINATION, straitFlush2.getCards());
    }

    @Test
    public void testSpadeStraitFlush() {
        final Combination straitFlush2 = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_SPADE_FULL);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlush2.getCombinationType());
        assertEquals(STRAIT_FLUSH_SPADE_COMBINATION, straitFlush2.getCards());
    }

    @Test
    public void testStraitFlushWithAce() {
        final Combination straitFlushWithAce = checkSimpleCombinationService.findCombination(STRAIT_FLUSH_WITH_ACE_FULL);
        assertEquals(CombinationType.STRAIGHT_FLUSH, straitFlushWithAce.getCombinationType());
        assertEquals(STRAIT_FLUSH_WITH_ACE_COMBINATION, straitFlushWithAce.getCards());
    }

    @Test
    public void testKare() {
        final Combination kare = checkSimpleCombinationService.findCombination(KARE_FULL);
        assertEquals(CombinationType.KARE, kare.getCombinationType());
        assertEquals(KARE_COMBINATION, kare.getCards());
    }

    @Test
    public void testKare2() {
        final Combination kare = checkSimpleCombinationService.findCombination(KARE_FULL2);
        assertEquals(CombinationType.KARE, kare.getCombinationType());
        assertEquals(KARE_COMBINATION2, kare.getCards());
    }

    @Test
    public void testKare3() {
        final Combination kare = checkSimpleCombinationService.findCombination(KARE_FULL3);
        assertEquals(CombinationType.KARE, kare.getCombinationType());
        assertEquals(KARE_COMBINATION3, kare.getCards());
    }

    @Test
    public void testKare4() {
        final Combination kare = checkSimpleCombinationService.findCombination(KARE_FULL4);
        assertEquals(CombinationType.KARE, kare.getCombinationType());
        assertEquals(KARE_COMBINATION4, kare.getCards());
    }

    @Test
    public void testFullHouse() {
        final Combination fullHouse = checkSimpleCombinationService.findCombination(FULL_HOUSE_FULL);
        assertEquals(CombinationType.FULL_HOUSE, fullHouse.getCombinationType());
        assertEquals(FULL_HOUSE_COMBINATION, fullHouse.getCards());
    }

    @Test
    public void testFullHouse2() {
        final Combination fullHouse = checkSimpleCombinationService.findCombination(FULL_HOUSE_FULL2);
        assertEquals(CombinationType.FULL_HOUSE, fullHouse.getCombinationType());
        assertEquals(FULL_HOUSE_COMBINATION2, fullHouse.getCards());
    }

    @Test
    public void testFullHouse3() {
        final Combination fullHouse = checkSimpleCombinationService.findCombination(FULL_HOUSE_FULL3);
        assertEquals(CombinationType.FULL_HOUSE, fullHouse.getCombinationType());
        assertEquals(FULL_HOUSE_COMBINATION3, fullHouse.getCards());
    }


    @Test
    public void testFullHouse4() {
        final Combination fullHouse = checkSimpleCombinationService.findCombination(FULL_HOUSE_FULL4);
        assertEquals(CombinationType.FULL_HOUSE, fullHouse.getCombinationType());
        assertEquals(FULL_HOUSE_COMBINATION4, fullHouse.getCards());
    }

    @Test
    public void testHeartFlush() {
        final Combination flush = checkSimpleCombinationService.findCombination(FLUSH_HEART_FULL);
        assertEquals(CombinationType.FLUSH, flush.getCombinationType());
        assertEquals(FLUSH_HEART_COMBINATION, flush.getCards());
    }

    @Test
    public void testClubFlush() {
        final Combination flush = checkSimpleCombinationService.findCombination(FLUSH_CLUB_FULL);
        assertEquals(CombinationType.FLUSH, flush.getCombinationType());
        assertEquals(FLUSH_CLUB_COMBINATION, flush.getCards());
    }

    @Test
    public void testDiamondFlush() {
        final Combination flush = checkSimpleCombinationService.findCombination(FLUSH_DIAMOND_FULL);
        assertEquals(CombinationType.FLUSH, flush.getCombinationType());
        assertEquals(FLUSH_DIAMOND_COMBINATION, flush.getCards());
    }

    @Test
    public void testSpadeFlush() {
        final Combination flush = checkSimpleCombinationService.findCombination(FLUSH_SPADE_FULL);
        assertEquals(CombinationType.FLUSH, flush.getCombinationType());
        assertEquals(FLUSH_SPADE_COMBINATION, flush.getCards());
    }

    @Test
    public void testStrait() {
        final Combination straight = checkSimpleCombinationService.findCombination(STRAIT_FULL);
        assertEquals(CombinationType.STRAIGHT, straight.getCombinationType());
        assertEquals(STRAIT_COMBINATION, straight.getCards());
    }

    @Test
    public void testStrait2() {
        final Combination straight2 = checkSimpleCombinationService.findCombination(STRAIT_FULL_2);
        assertEquals(CombinationType.STRAIGHT, straight2.getCombinationType());
        assertEquals(STRAIT_COMBINATION_2, straight2.getCards());
    }

    @Test
    public void testStraight3() {
        Combination strait3 = checkSimpleCombinationService.findCombination(STRAIT_FULL_3);
        assertEquals(CombinationType.STRAIGHT, strait3.getCombinationType());
        assertEquals(STRAIT_COMBINATION_3, strait3.getCards());
    }

    @Test
    public void testStraight4() {
        Combination strait4 = checkSimpleCombinationService.findCombination(STRAIT_FULL_4);
        assertEquals(CombinationType.STRAIGHT, strait4.getCombinationType());
        assertEquals(STRAIT_COMBINATION_4, strait4.getCards());
    }

    @Test
    public void testStraight5() {
        Combination strait5 = checkSimpleCombinationService.findCombination(STRAIT_FULL_5);
        assertEquals(CombinationType.STRAIGHT, strait5.getCombinationType());
        assertEquals(STRAIT_COMBINATION_5, strait5.getCards());
    }

    @Test
    public void testStraight6() {
        Combination strait6 = checkSimpleCombinationService.findCombination(STRAIT_FULL_6);
        assertEquals(CombinationType.STRAIGHT, strait6.getCombinationType());
        assertEquals(STRAIT_COMBINATION_6, strait6.getCards());
    }

    @Test
    public void testStraight7() {
        Combination strait7 = checkSimpleCombinationService.findCombination(STRAIT_FULL_7);
        assertEquals(CombinationType.STRAIGHT, strait7.getCombinationType());
        assertEquals(STRAIT_COMBINATION_7, strait7.getCards());
    }

    @Test
    public void testStraight8() {
        Combination strait8 = checkSimpleCombinationService.findCombination(STRAIT_FULL_8);
        assertEquals(CombinationType.STRAIGHT, strait8.getCombinationType());
        assertEquals(STRAIT_COMBINATION_8, strait8.getCards());
    }

    @Test
    public void testThree() {
        final Combination three = checkSimpleCombinationService.findCombination(THREE_FULL);
        assertEquals(CombinationType.THREE_CARDS, three.getCombinationType());
        assertEquals(THREE_COMBINATION, three.getCards());
    }

    @Test
    public void testThree2() {
        final Combination three_2 = checkSimpleCombinationService.findCombination(THREE_FULL_2);
        assertEquals(CombinationType.THREE_CARDS, three_2.getCombinationType());
        assertEquals(THREE_COMBINATION_2, three_2.getCards());
    }

    @Test
    public void testThree3() {
        final Combination three_3 = checkSimpleCombinationService.findCombination(THREE_FULL_3);
        assertEquals(CombinationType.THREE_CARDS, three_3.getCombinationType());
        assertEquals(THREE_COMBINATION_3, three_3.getCards());
    }

    @Test
    public void testTwoPair() {
        final Combination twoPair = checkSimpleCombinationService.findCombination(TWO_PAIR_FULL);
        assertEquals(CombinationType.TWO_PAIR, twoPair.getCombinationType());
        assertEquals(TWO_PAIR_COMBINATION, twoPair.getCards());
    }

    @Test
    public void testOnePair() {
        final Combination pair = checkSimpleCombinationService.findCombination(PAIR_FULL);
        assertEquals(CombinationType.ONE_PAIR, pair.getCombinationType());
        assertEquals(PAIR_FULL_COMBINATION, pair.getCards());
    }

    @Test
    public void testHighCard() {
        final Combination highCards = checkSimpleCombinationService.findCombination(HIGH_CARD_FULL);
        assertEquals(CombinationType.HIGH_CARD, highCards.getCombinationType());
        assertEquals(HIGH_COMBINATION, highCards.getCards());
    }

    @Test
    public void testHighCard2() {
        final Combination highCards_2 = checkSimpleCombinationService.findCombination(HIGH_CARD_FULL_2);
        assertEquals(CombinationType.HIGH_CARD, highCards_2.getCombinationType());
        assertEquals(HIGH_COMBINATION_2, highCards_2.getCards());
    }

}
