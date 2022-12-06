package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardSizeData;
import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

class FlushStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();

        final CardType.SuitType randomSuit = RandomUtils.getRandomSuit();

        final List<CardType> suitCards = cards.stream()
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
