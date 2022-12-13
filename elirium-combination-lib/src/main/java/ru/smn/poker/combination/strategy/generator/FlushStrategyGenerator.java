package ru.smn.poker.combination.strategy.generator;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.CardSizeData;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

class FlushStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card.SuitType randomSuit = RandomUtils.getRandomSuit();

        final List<Card> suitCards = cards.stream()
                .filter(cardType -> cardType.getSuitType().equals(randomSuit))
                .filter(cardType -> cardType.getPower().getPowerAsInt() % 2 == 0)
                .limit(CardSizeData.COMBINATION_SIZE)
                .collect(Collectors.toList());

        return Combination.of(
                CombinationType.FLUSH,
                suitCards
        );
    }
}
