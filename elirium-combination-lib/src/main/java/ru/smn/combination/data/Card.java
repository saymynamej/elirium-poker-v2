package ru.smn.combination.data;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.smn.combination.data.Card.SuitType.*;
import static ru.smn.combination.data.PowerType.*;


public enum Card {
    A_D(A_POWER, DIAMOND),
    K_D(K_POWER, DIAMOND),
    Q_D(Q_POWER, DIAMOND),
    J_D(J_POWER, DIAMOND),
    TEN_D(TEN_POWER, DIAMOND),
    NINE_D(NINE_POWER, DIAMOND),
    EIGHT_D(EIGHT_POWER, DIAMOND),
    SEVEN_D(SEVEN_POWER, DIAMOND),
    SIX_D(SIX_POWER, DIAMOND),
    FIVE_D(FIVE_POWER, DIAMOND),
    FOUR_D(FOUR_POWER, DIAMOND),
    THREE_D(THREE_POWER, DIAMOND),
    TWO_D(TWO_POWER, DIAMOND),
    A_H(A_POWER, HEART),
    K_H(K_POWER, HEART),
    Q_H(Q_POWER, HEART),
    J_H(J_POWER, HEART),
    TEN_H(TEN_POWER, HEART),
    NINE_H(NINE_POWER, HEART),
    EIGHT_H(EIGHT_POWER, HEART),
    SEVEN_H(SEVEN_POWER, HEART),
    SIX_H(SIX_POWER, HEART),
    FIVE_H(FIVE_POWER, HEART),
    FOUR_H(FOUR_POWER, HEART),
    THREE_H(THREE_POWER, HEART),
    TWO_H(TWO_POWER, HEART),
    A_S(A_POWER, SPADE),
    K_S(K_POWER, SPADE),
    Q_S(Q_POWER, SPADE),
    J_S(J_POWER, SPADE),
    TEN_S(TEN_POWER, SPADE),
    NINE_S(NINE_POWER, SPADE),
    EIGHT_S(EIGHT_POWER, SPADE),
    SEVEN_S(SEVEN_POWER, SPADE),
    SIX_S(SIX_POWER, SPADE),
    FIVE_S(FIVE_POWER, SPADE),
    FOUR_S(FOUR_POWER, SPADE),
    THREE_S(THREE_POWER, SPADE),
    TWO_S(TWO_POWER, SPADE),
    A_C(A_POWER, CLUB),
    K_C(K_POWER, CLUB),
    Q_C(Q_POWER, CLUB),
    J_C(J_POWER, CLUB),
    TEN_C(TEN_POWER, CLUB),
    NINE_C(NINE_POWER, CLUB),
    EIGHT_C(EIGHT_POWER, CLUB),
    SEVEN_C(SEVEN_POWER, CLUB),
    SIX_C(SIX_POWER, CLUB),
    FIVE_C(FIVE_POWER, CLUB),
    FOUR_C(FOUR_POWER, CLUB),
    THREE_C(THREE_POWER, CLUB),
    TWO_C(TWO_POWER, CLUB);

    @Getter
    private final PowerType power;
    @Getter
    private final SuitType suitType;

    Card(PowerType powerType, SuitType suitType) {
        this.power = powerType;
        this.suitType = suitType;
    }

    public static List<Card> getAllCardsAsList() {
        return Arrays.stream(values())
                .collect(Collectors.toList());
    }

    public static List<Card> getAllCardsAsListWithFilter(List<Card> filter) {
        return getAllCardsAsList().stream()
                .filter(card -> !filter.contains(card))
                .collect(Collectors.toList());
    }

    public int getPowerAsInt() {
        return power.getPowerAsInt();
    }

    public enum SuitType {
        DIAMOND,
        HEART,
        CLUB,
        SPADE
    }
}
