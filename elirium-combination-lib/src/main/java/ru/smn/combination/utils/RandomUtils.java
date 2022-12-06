package ru.smn.combination.utils;

import ru.smn.combination.data.CardType;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    public final static Random random = new Random();

    public static CardType.SuitType getRandomSuit() {
        final CardType.SuitType[] values = CardType.SuitType.values();
        return values[random.nextInt(values.length)];
    }

    public static CardType getRandomCard(List<CardType> cards) {
        return cards.get(random.nextInt(cards.size()));
    }
}
