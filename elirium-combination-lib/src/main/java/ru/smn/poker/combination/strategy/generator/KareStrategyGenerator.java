package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;
import ru.smn.poker.combination.utils.RandomUtils;

import java.util.List;

class QuadsStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card randomCardForQuads = RandomUtils.getRandomCard(cards);

        final List<Card> quads = CardUtils.getCardsWithPower(cards, randomCardForQuads.getPower(), 4);

        CardUtils.removeCardsWithPower(cards, randomCardForQuads.getPower());

        final Card highCard = RandomUtils.getRandomCard(cards);

        quads.add(highCard);

        return Combination.of(CombinationType.QUADS, quads);
    }
}
