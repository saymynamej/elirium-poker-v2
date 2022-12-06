package ru.smn.combination.strategy.search;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.data.PowerType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.smn.combination.data.CardSizeData.PAIR_SIZE;
import static ru.smn.combination.data.CardSizeData.THREE_SIZE;
import static ru.smn.combination.utils.CardUtils.findPowerOfCardWithFilter;

class FullHouseSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<CardType> cards) {
        final Predicate<Map.Entry<PowerType, Long>> conditionForThreeCards = (powerType) -> powerType.getValue() == 3;
        final Optional<PowerType> threeRepeats = findPowerOfCardWithFilter(cards, conditionForThreeCards);

        if (threeRepeats.isEmpty()) {
            return Combination.empty();
        }

        final Predicate<Map.Entry<PowerType, Long>> conditionForTwoCards = (powerType) -> powerType.getValue() > 1 && !powerType.getKey().equals(threeRepeats.get());
        final Optional<PowerType> twoOrMore = findPowerOfCardWithFilter(cards, conditionForTwoCards);

        if (twoOrMore.isEmpty()) {
            return Combination.empty();
        }

        final List<CardType> threeCards = cards.stream()
                .filter(cardType -> cardType.getPower().equals(threeRepeats.get()))
                .limit(THREE_SIZE)
                .toList();

        final List<CardType> twoCards = cards.stream()
                .filter(cardType -> cardType.getPower().equals(twoOrMore.get()))
                .limit(PAIR_SIZE)
                .toList();

        final List<CardType> combination = Stream.concat(threeCards.stream(), twoCards.stream()).collect(Collectors.toList());

        final CombinationType fullHouse = CombinationType.FULL_HOUSE;

        return Combination.of(fullHouse, combination);
    }
}
