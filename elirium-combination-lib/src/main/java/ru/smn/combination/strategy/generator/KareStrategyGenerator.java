package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;

class KareStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card randomCardForKare = RandomUtils.getRandomCard(cards);

        final List<Card> kare = CardUtils.getCardsWithPower(cards, randomCardForKare.getPower(), 4);

        CardUtils.removeCardsWithPower(cards, randomCardForKare.getPower());

        final Card highCard = RandomUtils.getRandomCard(cards);

        kare.add(highCard);

        return Combination.of(CombinationType.KARE, kare);
    }
}
