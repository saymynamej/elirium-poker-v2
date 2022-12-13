package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.List;
import java.util.stream.Collectors;

import static ru.smn.poker.combination.data.CardSizeData.COMBINATION_SIZE;
import static ru.smn.poker.combination.utils.CardUtils.sortByDesc;

class HighCardSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final List<Card> highCardCombination = sortByDesc(cards)
                .stream()
                .limit(COMBINATION_SIZE)
                .collect(Collectors.toList());

        return Combination.of(CombinationType.HIGH_CARD, highCardCombination);
    }
}
