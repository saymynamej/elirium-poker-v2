package ru.smn.poker.combination.data;

public enum CombinationType {
    FLUSH_ROYAL(10),
    STRAIGHT_FLUSH(9),
    KARE(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_CARDS(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int power;

    CombinationType(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
