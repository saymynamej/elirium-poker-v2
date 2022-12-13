package ru.smn.poker.combination.data;


public enum PowerType {
    A_POWER(14),
    K_POWER(13),
    Q_POWER(12),
    J_POWER(11),
    TEN_POWER(10),
    NINE_POWER(9),
    EIGHT_POWER(8),
    SEVEN_POWER(7),
    SIX_POWER(6),
    FIVE_POWER(5),
    FOUR_POWER(4),
    THREE_POWER(3),

    TWO_POWER(2);


    private final int power;

    PowerType(int power) {
        this.power = power;
    }

    public int getPowerAsInt() {
        return power;
    }
}
