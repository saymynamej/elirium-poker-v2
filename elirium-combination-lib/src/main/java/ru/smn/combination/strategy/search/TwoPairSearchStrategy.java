package ru.smn.combination.strategy.search;

import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.smn.combination.data.CardSizeData.PAIR_SIZE;
import static ru.smn.combination.data.CardSizeData.TWO_PAIR_SIZE;
import static ru.smn.combination.utils.CardUtils.findTheBiggestCardIgnoringFilter;

class TwoPairSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final List<Card> twoPairs = cards.stream()
                .collect(Collectors.groupingBy(Card::getPowerAsInt))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() == PAIR_SIZE)
                .sorted(Map.Entry.<Integer, List<Card>>comparingByKey().reversed())
                .limit(2)
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());

        if (twoPairs.isEmpty() || twoPairs.size() < TWO_PAIR_SIZE) {
            return Combination.empty();
        }

        twoPairs.add(findTheBiggestCardIgnoringFilter(cards, twoPairs.get(0).getPower()));

        return Combination.of(CombinationType.TWO_PAIR, twoPairs);
    }
}
