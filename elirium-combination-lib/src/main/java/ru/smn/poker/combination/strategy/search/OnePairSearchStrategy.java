package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.CardSizeData;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OnePairSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final List<Card> pair = cards.stream()
                .collect(Collectors.groupingBy(Card::getPowerAsInt))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() == CardSizeData.PAIR_SIZE)
                .flatMap(entry -> entry.getValue().stream())
                .toList();

        if (pair.isEmpty()) {
            return Combination.empty();
        }

        final List<Card> highCards = CardUtils.sortByDesc(cards).stream()
                .filter(cardType -> cardType.getPowerAsInt() != pair.get(0).getPowerAsInt())
                .limit(3)
                .toList();

        final List<Card> pairCombination = Stream.concat(pair.stream(), highCards.stream())
                .collect(Collectors.toList());

        return Combination.of(CombinationType.ONE_PAIR, pairCombination);
    }
}
