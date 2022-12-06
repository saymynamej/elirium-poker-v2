package ru.smn.combination.data;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(of = "combinationType")
@Builder
public class Combination {
    private final CombinationType combinationType;
    private final List<CardType> cards;
    private final Integer power;

    public static Combination empty() {
        return Combination.builder()
                .power(null)
                .cards(null)
                .combinationType(null)
                .build();
    }

    public static Combination of(CombinationType type, List<CardType> cards) {
        return Combination.builder()
                .combinationType(type)
                .cards(cards)
                .build();
    }

    public static Combination of(CombinationType type, List<CardType> cards, Integer power) {
        return Combination.builder()
                .combinationType(type)
                .cards(cards)
                .power(power)
                .build();
    }

    public boolean isEmpty() {
        return cards == null || cards.isEmpty();
    }
}
