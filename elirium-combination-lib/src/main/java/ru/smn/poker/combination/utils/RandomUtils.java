package ru.smn.poker.combination.utils;

import ru.smn.poker.combination.data.Card;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    public final static Random random = new Random();

    public static Card.SuitType getRandomSuit() {
        final Card.SuitType[] values = Card.SuitType.values();
        return values[random.nextInt(values.length)];
    }

    public static Card getRandomCard(List<Card> cards) {
        return cards.get(random.nextInt(cards.size()));
    }
}
