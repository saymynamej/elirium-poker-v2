package ru.smn.combination.strategy.search;

import ru.smn.combination.data.CardSizeData;
import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

import java.util.List;
import java.util.stream.Collectors;

import static ru.smn.combination.utils.CardUtils.findTheBiggestCardIgnoringFilter;

class KareSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<CardType> cards) {
        final int firstIndexOfCard = 0;

        final List<CardType> kareCards = cards.stream()
                .collect(Collectors.groupingBy(CardType::getPowerAsInt))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == CardSizeData.KARE_SIZE)
                .peek(entry -> entry.getValue().add(findTheBiggestCardIgnoringFilter(cards, entry.getValue().get(firstIndexOfCard).getPower())))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());

        if (kareCards.isEmpty()) {
            return Combination.empty();
        }

        return Combination.of(CombinationType.KARE, kareCards);

    }
}
