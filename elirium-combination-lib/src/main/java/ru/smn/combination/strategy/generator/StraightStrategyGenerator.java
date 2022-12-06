package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardSizeData;
import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

class StraightStrategyGenerator implements GeneratorStrategy {

    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();
        final CardType randomCard = RandomUtils.getRandomCard(cards);

        final List<CardType> straight = cards.stream()
                .filter(cardType -> cardType.getPower() != randomCard.getPower())
                .filter(cardType -> cardType.getPowerAsInt() - randomCard.getPowerAsInt() < CardSizeData.COMBINATION_SIZE)
                .filter(cardType -> !cardType.getSuitType().equals(randomCard.getSuitType()))
                .limit(4)
                .collect(Collectors.toList());

        straight.add(randomCard);

        return Combination.of(
                CombinationType.STRAIGHT,
                straight
        );
    }
}
