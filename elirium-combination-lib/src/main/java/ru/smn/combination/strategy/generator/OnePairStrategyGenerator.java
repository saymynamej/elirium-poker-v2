package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

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
