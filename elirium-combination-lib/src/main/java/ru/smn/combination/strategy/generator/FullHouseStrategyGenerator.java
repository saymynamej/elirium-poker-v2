package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FullHouseStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();

        final CardType randomCardForThree = RandomUtils.getRandomCard(cards);

        final List<CardType> threeCards = CardUtils.getCardsWithPower(cards, randomCardForThree.getPower(), 3);

        CardUtils.removeCardsWithPower(cards, randomCardForThree.getPower());

        final CardType randomCardForTwo = RandomUtils.getRandomCard(cards);

        final List<CardType> twoCards = CardUtils.getCardsWithPower(cards, randomCardForTwo.getPower(), 2);

        return Combination.of(
                CombinationType.FULL_HOUSE,
                Stream.concat(threeCards.stream(), twoCards.stream()).collect(Collectors.toList()
                )
        );
    }
}
