package ru.smn.poker.combination.data;

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
    private final List<Card> cards;

    public static Combination empty() {
        return Combination.builder()
                .cards(null)
                .combinationType(null)
                .build();
    }

    public static Combination of(CombinationType type, List<Card> cards) {
        return Combination.builder()
                .combinationType(type)
                .cards(cards)
                .build();
    }

    public boolean isEmpty() {
        return cards == null || cards.isEmpty();
    }

    public List<Card> getCards() {
        return cards;
    }
}
