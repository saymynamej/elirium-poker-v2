package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;
import ru.smn.poker.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FullHouseStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card randomCardForThree = RandomUtils.getRandomCard(cards);

        final List<Card> threeCards = CardUtils.getCardsWithPower(cards, randomCardForThree.getPower(), 3);

        CardUtils.removeCardsWithPower(cards, randomCardForThree.getPower());

        final Card randomCardForTwo = RandomUtils.getRandomCard(cards);

        final List<Card> twoCards = CardUtils.getCardsWithPower(cards, randomCardForTwo.getPower(), 2);

        return Combination.of(
                CombinationType.FULL_HOUSE,
                Stream.concat(threeCards.stream(), twoCards.stream()).collect(Collectors.toList()
                )
        );
    }
}
