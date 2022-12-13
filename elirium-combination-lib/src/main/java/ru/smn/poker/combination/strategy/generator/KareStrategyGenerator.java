package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;
import ru.smn.poker.combination.utils.RandomUtils;

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
