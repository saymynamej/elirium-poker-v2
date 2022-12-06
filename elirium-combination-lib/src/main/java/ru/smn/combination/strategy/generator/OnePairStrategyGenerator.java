package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;

class OnePairStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();

        final CardType randomCard = RandomUtils.getRandomCard(cards);

        final List<CardType> pair = CardUtils.getCardsWithPower(cards, randomCard.getPower(), 2);

        CardUtils.removeCardsWithPower(cards, randomCard.getPower());

        for (int i = 0; i < 3; i++) {
            final CardType randomHighCard = RandomUtils.getRandomCard(cards);
            pair.add(randomHighCard);
            CardUtils.removeCardsWithPower(cards, randomHighCard.getPower());
        }

        return Combination.of(
                CombinationType.ONE_PAIR,
                pair
        );
    }
}
