package ru.smn.combination.strategy.search;

import ru.smn.combination.data.CardSizeData;
import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OnePairSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<CardType> cards) {
        final List<CardType> pair = cards.stream()
                .collect(Collectors.groupingBy(CardType::getPowerAsInt))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() == CardSizeData.PAIR_SIZE)
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());

        if (pair.isEmpty()) {
            return Combination.empty();
        }

        final List<CardType> highCards = CardUtils.sortByDesc(cards).stream()
                .filter(cardType -> cardType.getPowerAsInt() != pair.get(0).getPowerAsInt())
                .limit(3)
                .collect(Collectors.toList());

        final List<CardType> pairCombination = Stream.concat(pair.stream(), highCards.stream())
                .collect(Collectors.toList());

        return Combination.of(CombinationType.ONE_PAIR, pairCombination);
    }
}
