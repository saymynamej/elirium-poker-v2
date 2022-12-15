package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.CardSizeData;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.List;
import java.util.stream.Collectors;

import static ru.smn.poker.combination.utils.CardUtils.findTheBiggestCardIgnoringFilter;

class QuadsSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final int firstIndexOfCard = 0;

        final List<Card> quadsCards = cards.stream()
                .collect(Collectors.groupingBy(Card::getPowerAsInt))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == CardSizeData.Quads_SIZE)
                .peek(entry -> entry.getValue().add(findTheBiggestCardIgnoringFilter(cards, entry.getValue().get(firstIndexOfCard).getPower())))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());

        if (quadsCards.isEmpty()) {
            return Combination.empty();
        }

        return Combination.of(CombinationType.QUADS, quadsCards);

    }
}
