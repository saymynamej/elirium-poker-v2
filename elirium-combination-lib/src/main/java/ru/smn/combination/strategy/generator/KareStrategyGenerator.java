package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.CardUtils;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;

class KareStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();

        final CardType randomCardForKare = RandomUtils.getRandomCard(cards);

        final List<CardType> kare = CardUtils.getCardsWithPower(cards, randomCardForKare.getPower(), 4);

        CardUtils.removeCardsWithPower(cards, randomCardForKare.getPower());

        final CardType highCard = RandomUtils.getRandomCard(cards);

        kare.add(highCard);

        return Combination.of(CombinationType.KARE, kare);
    }
}
