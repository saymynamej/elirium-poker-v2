package ru.smn.combination.strategy.search;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.smn.combination.data.CardSizeData.THREE_SIZE;

class ThreeCardsSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<CardType> cards) {
        final List<CardType> threeCards = cards.stream()
                .collect(Collectors.groupingBy(CardType::getPowerAsInt))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() == THREE_SIZE)
                .sorted(Map.Entry.<Integer, List<CardType>>comparingByKey().reversed())
                .limit(1)
                .flatMap(entry -> entry.getValue().stream())
                .toList();

        if (threeCards.isEmpty()) {
            return Combination.empty();
        }

        final List<CardType> highCards = cards.stream()
                .filter(cardType -> !threeCards.contains(cardType))
                .sorted(Comparator.comparingInt(CardType::getPowerAsInt).reversed())
                .limit(2)
                .toList();

        final List<CardType> combination = Stream.concat(threeCards.stream(), highCards.stream())
                .collect(Collectors.toList());

        return Combination.of(CombinationType.THREE_CARDS, combination);

    }
}
