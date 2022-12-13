package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;
import ru.smn.poker.combination.utils.RandomUtils;

import java.util.List;

class OnePairStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card randomCard = RandomUtils.getRandomCard(cards);

        final List<Card> pair = CardUtils.getCardsWithPower(cards, randomCard.getPower(), 2);

        CardUtils.removeCardsWithPower(cards, randomCard.getPower());

        for (int i = 0; i < 3; i++) {
            final Card randomHighCard = RandomUtils.getRandomCard(cards);
            pair.add(randomHighCard);
            CardUtils.removeCardsWithPower(cards, randomHighCard.getPower());
        }

        return Combination.of(
                CombinationType.ONE_PAIR,
                pair
        );
    }
}
