package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;

class ThreeStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();
        final Card randomCardForThree = RandomUtils.getRandomCard(cards);
        final List<Card> combination = CardUtils.getCardsWithPower(cards, randomCardForThree.getPower(), 3);

        CardUtils.removeCardsWithPower(cards, randomCardForThree.getPower());

        for (int i = 0; i < 2; i++) {
            final Card randomCard = RandomUtils.getRandomCard(cards);
            combination.add(randomCard);
            CardUtils.removeCardsWithPower(cards, randomCard.getPower());
        }

        return Combination.of(
                CombinationType.THREE_CARDS,
                combination
        );
    }
}
