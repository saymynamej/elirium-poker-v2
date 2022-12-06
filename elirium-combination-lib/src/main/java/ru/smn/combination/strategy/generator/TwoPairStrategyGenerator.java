package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

class TwoPairStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();
        final List<CardType> combination = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            final CardType randomCard = RandomUtils.getRandomCard(cards);
            final List<CardType> pair = CardUtils.getCardsWithPower(cards, randomCard.getPower(), 2);
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
