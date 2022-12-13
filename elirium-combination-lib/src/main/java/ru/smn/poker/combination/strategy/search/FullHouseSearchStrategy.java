package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.data.PowerType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.smn.poker.combination.data.CardSizeData.PAIR_SIZE;
import static ru.smn.poker.combination.data.CardSizeData.THREE_SIZE;
import static ru.smn.poker.combination.utils.CardUtils.findPowerOfCardWithFilter;

class FullHouseSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
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

        final List<Card> threeCards = cards.stream()
                .filter(cardType -> cardType.getPower().equals(threeRepeats.get()))
                .limit(THREE_SIZE)
                .toList();

        final List<Card> twoCards = cards.stream()
                .filter(cardType -> cardType.getPower().equals(twoOrMore.get()))
                .limit(PAIR_SIZE)
                .toList();

        final List<Card> combination = Stream.concat(threeCards.stream(), twoCards.stream()).collect(Collectors.toList());

        final CombinationType fullHouse = CombinationType.FULL_HOUSE;

        return Combination.of(fullHouse, combination);
    }
}
