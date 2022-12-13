package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.data.PowerType;
import ru.smn.poker.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

class FlushRoyalStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card.SuitType suitForFlushRoyal = RandomUtils.getRandomSuit();

        final List<Card> flushRoyal = cards.stream()
                .filter(cardType -> cardType.getSuitType().equals(suitForFlushRoyal))
                .takeWhile(cardType -> cardType.getPower().getPowerAsInt() >= PowerType.TEN_POWER.getPowerAsInt())
                .collect(Collectors.toList());

        return Combination.of(
                CombinationType.FLUSH_ROYAL,
                flushRoyal
        );
    }
}
