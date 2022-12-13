package ru.smn.poker.combination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicCombinationServiceTest {

    private static final List<Card> FLUSH_ROYAL_HEART_FULL = new ArrayList<>(Arrays.asList(Card.A_H, Card.K_H, Card.Q_H, Card.J_H, Card.TEN_H, Card.FOUR_H, Card.K_S));
    private static final List<Card> FLUSH_ROYAL_HEART_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_H, Card.K_H, Card.Q_H, Card.J_H, Card.TEN_H));
    private static final List<Card> FLUSH_ROYAL_SPADE_FULL = new ArrayList<>(Arrays.asList(Card.FOUR_H, Card.TWO_H, Card.A_S, Card.K_S, Card.Q_S, Card.J_S, Card.TEN_S));
    private static final List<Card> FLUSH_ROYAL_SPADE_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_S, Card.K_S, Card.Q_S, Card.J_S, Card.TEN_S));
    private static final List<Card> FLUSH_ROYAL_DIAMOND_FULL = new ArrayList<>(Arrays.asList(Card.TWO_C, Card.TEN_D, Card.A_D, Card.K_D, Card.Q_D, Card.J_D, Card.SEVEN_C));
    private static final List<Card> FLUSH_ROYAL_DIAMOND_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_D, Card.K_D, Card.Q_D, Card.J_D, Card.TEN_D));
    private static final List<Card> FLUSH_ROYAL_CLUB_FULL = new ArrayList<>(Arrays.asList(Card.A_H, Card.K_H, Card.TEN_C, Card.J_C, Card.Q_C, Card.K_C, Card.A_C));
    private static final List<Card> FLUSH_ROYAL_CLUB_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_C, Card.K_C, Card.Q_C, Card.J_C, Card.TEN_C));
    private static final List<Card> STRAIT_FLUSH_CLUB_FULL = new ArrayList<>(Arrays.asList(Card.EIGHT_C, Card.SEVEN_C, Card.TEN_C, Card.J_C, Card.NINE_C, Card.FOUR_H, Card.K_S));
    private static final List<Card> STRAIT_FLUSH_CLUB_COMBINATION = new ArrayList<>(Arrays.asList(Card.J_C, Card.TEN_C, Card.NINE_C, Card.EIGHT_C, Card.SEVEN_C));
    private static final List<Card> STRAIT_FLUSH_HEART_FULL = new ArrayList<>(Arrays.asList(Card.TWO_H, Card.FOUR_D, Card.EIGHT_H, Card.NINE_H, Card.TEN_H, Card.J_H, Card.Q_H));
    private static final List<Card> STRAIT_FLUSH_HEART_COMBINATION = new ArrayList<>(Arrays.asList(Card.Q_H, Card.J_H, Card.TEN_H, Card.NINE_H, Card.EIGHT_H));
    private static final List<Card> STRAIT_FLUSH_DIAMOND_FULL = new ArrayList<>(Arrays.asList(Card.TWO_H, Card.THREE_D, Card.FOUR_D, Card.FIVE_D, Card.SIX_D, Card.SEVEN_D, Card.Q_H));
    private static final List<Card> STRAIT_FLUSH_DIAMOND_COMBINATION = new ArrayList<>(Arrays.asList(Card.SEVEN_D, Card.SIX_D, Card.FIVE_D, Card.FOUR_D, Card.THREE_D));
    private static final List<Card> STRAIT_FLUSH_SPADE_FULL = new ArrayList<>(Arrays.asList(Card.TWO_S, Card.THREE_S, Card.FOUR_S, Card.FIVE_S, Card.SIX_S, Card.SEVEN_S, Card.EIGHT_S));
    private static final List<Card> STRAIT_FLUSH_SPADE_COMBINATION = new ArrayList<>(Arrays.asList(Card.EIGHT_S, Card.SEVEN_S, Card.SIX_S, Card.FIVE_S, Card.FOUR_S));
    private static final List<Card> STRAIT_FLUSH_FULL = new ArrayList<>(Arrays.asList(Card.TWO_H, Card.SEVEN_C, Card.EIGHT_C, Card.NINE_C, Card.TEN_C, Card.J_C, Card.K_H));
    private static final List<Card> STRAIT_FLUSH_COMBINATION = new ArrayList<>(Arrays.asList(Card.J_C, Card.TEN_C, Card.NINE_C, Card.EIGHT_C, Card.SEVEN_C));
    private static final List<Card> STRAIT_FLUSH_FULL_2 = new ArrayList<>(Arrays.asList(Card.EIGHT_S, Card.NINE_S, Card.Q_S, Card.TWO_D, Card.THREE_C, Card.J_S, Card.TEN_S));
    private static final List<Card> STRAIT_FLUSH_COMBINATION_2 = new ArrayList<>(Arrays.asList(Card.Q_S, Card.J_S, Card.TEN_S, Card.NINE_S, Card.EIGHT_S));
    private static final List<Card> STRAIT_FLUSH_WITH_ACE_FULL = new ArrayList<>(Arrays.asList(Card.A_H, Card.TWO_H, Card.THREE_H, Card.FOUR_H, Card.FIVE_H, Card.J_C, Card.K_H));
    private static final List<Card> STRAIT_FLUSH_WITH_ACE_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_H, Card.FIVE_H, Card.FOUR_H, Card.THREE_H, Card.TWO_H));
    private static final List<Card> KARE_FULL = new ArrayList<>(Arrays.asList(Card.NINE_C, Card.FOUR_H, Card.K_S, Card.SIX_C, Card.SIX_H, Card.SIX_S, Card.SIX_D));
    private static final List<Card> KARE_COMBINATION = new ArrayList<>(Arrays.asList(Card.K_S, Card.SIX_C, Card.SIX_H, Card.SIX_S, Card.SIX_D));
    private static final List<Card> KARE_FULL2 = new ArrayList<>(Arrays.asList(Card.A_C, Card.A_D, Card.A_S, Card.A_H, Card.TEN_C, Card.SIX_D, Card.K_H));
    private static final List<Card> KARE_COMBINATION2 = new ArrayList<>(Arrays.asList(Card.A_C, Card.A_D, Card.A_S, Card.A_H, Card.K_H));
    private static final List<Card> KARE_FULL3 = new ArrayList<>(Arrays.asList(Card.Q_C, Card.FOUR_H, Card.NINE_C, Card.NINE_H, Card.NINE_D, Card.NINE_S, Card.SIX_D));
    private static final List<Card> KARE_COMBINATION3 = new ArrayList<>(Arrays.asList(Card.Q_C, Card.NINE_C, Card.NINE_H, Card.NINE_D, Card.NINE_S));
    private static final List<Card> KARE_FULL4 = new ArrayList<>(Arrays.asList(Card.SEVEN_S, Card.FOUR_H, Card.SEVEN_C, Card.SIX_C, Card.SEVEN_D, Card.SEVEN_H, Card.A_D));
    private static final List<Card> KARE_COMBINATION4 = new ArrayList<>(Arrays.asList(Card.A_D, Card.SEVEN_S, Card.SEVEN_C, Card.SEVEN_D, Card.SEVEN_H));
    private static final List<Card> FLUSH_HEART_FULL = new ArrayList<>(Arrays.asList(Card.NINE_H, Card.A_H, Card.K_H, Card.TWO_H, Card.FIVE_H, Card.FOUR_H, Card.K_S));
    private static final List<Card> FLUSH_HEART_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_H, Card.K_H, Card.NINE_H, Card.FIVE_H, Card.FOUR_H));
    private static final List<Card> FLUSH_DIAMOND_FULL = new ArrayList<>(Arrays.asList(Card.J_D, Card.TWO_D, Card.THREE_D, Card.SEVEN_D, Card.A_H, Card.A_C, Card.K_D));
    private static final List<Card> FLUSH_DIAMOND_COMBINATION = new ArrayList<>(Arrays.asList(Card.K_D, Card.J_D, Card.SEVEN_D, Card.THREE_D, Card.TWO_D));
    private static final List<Card> FLUSH_SPADE_FULL = new ArrayList<>(Arrays.asList(Card.K_S, Card.Q_S, Card.SEVEN_S, Card.FOUR_S, Card.FIVE_S, Card.K_H, Card.K_C));
    private static final List<Card> FLUSH_SPADE_COMBINATION = new ArrayList<>(Arrays.asList(Card.K_S, Card.Q_S, Card.SEVEN_S, Card.FIVE_S, Card.FOUR_S));
    private static final List<Card> FLUSH_CLUB_FULL = new ArrayList<>(Arrays.asList(Card.A_C, Card.K_C, Card.J_C, Card.Q_C, Card.NINE_C, Card.TWO_C, Card.THREE_C));
    private static final List<Card> FLUSH_CLUB_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_C, Card.K_C, Card.Q_C, Card.J_C, Card.NINE_C));
    private static final List<Card> FULL_HOUSE_FULL = new ArrayList<>(Arrays.asList(Card.EIGHT_C, Card.EIGHT_D, Card.EIGHT_H, Card.FIVE_D, Card.FIVE_C, Card.FIVE_H, Card.FOUR_H));
    private static final List<Card> FULL_HOUSE_COMBINATION = new ArrayList<>(Arrays.asList(Card.EIGHT_C, Card.EIGHT_D, Card.EIGHT_H, Card.FIVE_D, Card.FIVE_C));
    private static final List<Card> FULL_HOUSE_FULL2 = new ArrayList<>(Arrays.asList(Card.A_D, Card.A_C, Card.SEVEN_D, Card.A_H, Card.K_C, Card.K_H, Card.FOUR_H));
    private static final List<Card> FULL_HOUSE_COMBINATION2 = new ArrayList<>(Arrays.asList(Card.A_D, Card.A_C, Card.A_H, Card.K_C, Card.K_H));
    private static final List<Card> FULL_HOUSE_FULL3 = new ArrayList<>(Arrays.asList(Card.EIGHT_C, Card.EIGHT_D, Card.Q_C, Card.Q_H, Card.Q_D, Card.TEN_C, Card.TEN_D));
    private static final List<Card> FULL_HOUSE_COMBINATION3 = new ArrayList<>(Arrays.asList(Card.Q_C, Card.Q_H, Card.Q_D, Card.TEN_C, Card.TEN_D));
    private static final List<Card> FULL_HOUSE_FULL4 = new ArrayList<>(Arrays.asList(Card.J_D, Card.EIGHT_D, Card.SEVEN_H, Card.SEVEN_D, Card.FIVE_C, Card.J_C, Card.J_H));
    private static final List<Card> FULL_HOUSE_COMBINATION4 = new ArrayList<>(Arrays.asList(Card.J_D, Card.J_C, Card.J_H, Card.SEVEN_H, Card.SEVEN_D));
    private static final List<Card> STRAIT_FULL = new ArrayList<>(Arrays.asList(Card.EIGHT_C, Card.SEVEN_S, Card.SIX_H, Card.FIVE_D, Card.THREE_D, Card.FOUR_H, Card.TWO_D));
    private static final List<Card> STRAIT_COMBINATION = new ArrayList<>(Arrays.asList(Card.EIGHT_C, Card.SEVEN_S, Card.SIX_H, Card.FIVE_D, Card.FOUR_H));
    private static final List<Card> STRAIT_FULL_2 = new ArrayList<>(Arrays.asList(Card.THREE_H, Card.FIVE_C, Card.SEVEN_C, Card.EIGHT_C, Card.NINE_D, Card.TEN_H, Card.J_H));
    private static final List<Card> STRAIT_COMBINATION_2 = new ArrayList<>(Arrays.asList(Card.J_H, Card.TEN_H, Card.NINE_D, Card.EIGHT_C, Card.SEVEN_C));
    private static final List<Card> STRAIT_FULL_3 = new ArrayList<>(Arrays.asList(Card.A_D, Card.J_C, Card.TEN_C, Card.Q_C, Card.K_C, Card.J_H, Card.J_D));
    private static final List<Card> STRAIT_COMBINATION_3 = new ArrayList<>(Arrays.asList(Card.A_D, Card.K_C, Card.Q_C, Card.J_C, Card.TEN_C));
    private static final List<Card> STRAIT_FULL_4 = new ArrayList<>(Arrays.asList(Card.SEVEN_C, Card.EIGHT_C, Card.NINE_C, Card.TEN_H, Card.J_H, Card.Q_H, Card.K_D));
    private static final List<Card> STRAIT_COMBINATION_4 = new ArrayList<>(Arrays.asList(Card.K_D, Card.Q_H, Card.J_H, Card.TEN_H, Card.NINE_C));
    private static final List<Card> STRAIT_FULL_5 = new ArrayList<>(Arrays.asList(Card.SEVEN_C, Card.EIGHT_C, Card.NINE_C, Card.TEN_H, Card.J_H, Card.A_H, Card.K_H));
    private static final List<Card> STRAIT_COMBINATION_5 = new ArrayList<>(Arrays.asList(Card.J_H, Card.TEN_H, Card.NINE_C, Card.EIGHT_C, Card.SEVEN_C));
    private static final List<Card> STRAIT_FULL_6 = new ArrayList<>(Arrays.asList(Card.TWO_H, Card.SEVEN_C, Card.EIGHT_C, Card.NINE_C, Card.TEN_H, Card.J_H, Card.K_H));
    private static final List<Card> STRAIT_COMBINATION_6 = new ArrayList<>(Arrays.asList(Card.J_H, Card.TEN_H, Card.NINE_C, Card.EIGHT_C, Card.SEVEN_C));
    private static final List<Card> STRAIT_FULL_7 = new ArrayList<>(Arrays.asList(Card.TWO_H, Card.THREE_C, Card.FOUR_S, Card.FIVE_H, Card.SIX_C, Card.J_H, Card.K_H));
    private static final List<Card> STRAIT_COMBINATION_7 = new ArrayList<>(Arrays.asList(Card.SIX_C, Card.FIVE_H, Card.FOUR_S, Card.THREE_C, Card.TWO_H));
    private static final List<Card> STRAIT_FULL_8 = new ArrayList<>(Arrays.asList(Card.A_C, Card.TWO_C, Card.THREE_C, Card.FOUR_D, Card.FIVE_D, Card.J_H, Card.K_H));
    private static final List<Card> STRAIT_COMBINATION_8 = new ArrayList<>(Arrays.asList(Card.A_C, Card.FIVE_D, Card.FOUR_D, Card.THREE_C, Card.TWO_C));
    private static final List<Card> TWO_PAIR_FULL = new ArrayList<>(Arrays.asList(Card.A_H, Card.A_D, Card.FOUR_H, Card.FOUR_C, Card.SEVEN_S, Card.SEVEN_C, Card.K_D));
    private static final List<Card> TWO_PAIR_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_H, Card.A_D, Card.K_D, Card.SEVEN_S, Card.SEVEN_C));
    private static final List<Card> THREE_FULL = new ArrayList<>(Arrays.asList(Card.THREE_H, Card.THREE_C, Card.THREE_S, Card.K_D, Card.SEVEN_S, Card.FOUR_H, Card.NINE_C));
    private static final List<Card> THREE_COMBINATION = new ArrayList<>(Arrays.asList(Card.K_D, Card.NINE_C, Card.THREE_H, Card.THREE_C, Card.THREE_S));
    private static final List<Card> THREE_FULL_2 = new ArrayList<>(Arrays.asList(Card.SEVEN_S, Card.FOUR_H, Card.NINE_C, Card.A_D, Card.A_C, Card.A_H, Card.K_D));
    private static final List<Card> THREE_COMBINATION_2 = new ArrayList<>(Arrays.asList(Card.A_D, Card.A_C, Card.A_H, Card.K_D, Card.NINE_C));
    private static final List<Card> THREE_FULL_3 = new ArrayList<>(Arrays.asList(Card.SEVEN_S, Card.FOUR_H, Card.A_D, Card.NINE_C, Card.A_C, Card.A_H, Card.K_D));
    private static final List<Card> THREE_COMBINATION_3 = new ArrayList<>(Arrays.asList(Card.A_D, Card.A_C, Card.A_H, Card.K_D, Card.NINE_C));
    private static final List<Card> PAIR_FULL = new ArrayList<>(Arrays.asList(Card.THREE_H, Card.THREE_C, Card.A_C, Card.K_S, Card.TWO_D, Card.FOUR_D, Card.SEVEN_S));
    private static final List<Card> PAIR_FULL_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_C, Card.K_S, Card.SEVEN_S, Card.THREE_H, Card.THREE_C));
    private static final List<Card> HIGH_CARD_FULL = new ArrayList<>(Arrays.asList(Card.TWO_H, Card.TEN_C, Card.A_H, Card.K_D, Card.SEVEN_S, Card.FOUR_H, Card.NINE_C));
    private static final List<Card> HIGH_COMBINATION = new ArrayList<>(Arrays.asList(Card.A_H, Card.K_D, Card.TEN_C, Card.NINE_C, Card.SEVEN_S));
    private static final List<Card> HIGH_CARD_FULL_2 = new ArrayList<>(Arrays.asList(Card.A_C, Card.TEN_C, Card.FIVE_H, Card.K_D, Card.EIGHT_H, Card.FOUR_H, Card.NINE_C));
    private static final List<Card> HIGH_COMBINATION_2 = new ArrayList<>(Arrays.asList(Card.A_C, Card.K_D, Card.TEN_C, Card.NINE_C, Card.EIGHT_H));
    private final ClassicCombinationService checkSimpleCombinationService = new ClassicCombinationService();

    @Test
    public void testHeardFlushRoyal() {
        final Combination flashRoyal = checkSimpleCombinationService.findCombination(FLUSH_ROYAL_HEART_FULL);
        Assertions.assertEquals(CombinationType.FLUSH_ROYAL, flashRoyal.getCombinationType());
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
