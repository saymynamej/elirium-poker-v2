package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardSizeData;
import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.RandomUtils;

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
