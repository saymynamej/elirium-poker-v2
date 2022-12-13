package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;
import ru.smn.poker.combination.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

class TwoPairStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();
        final List<Card> combination = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            final Card randomCard = RandomUtils.getRandomCard(cards);
            final List<Card> pair = CardUtils.getCardsWithPower(cards, randomCard.getPower(), 2);
            combination.addAll(pair);
            CardUtils.removeCardsWithPower(cards, randomCard.getPower());
        }

        combination.add(RandomUtils.getRandomCard(cards));

        return Combination.of(
                CombinationType.TWO_PAIR,
                combination
        );
    }
}
