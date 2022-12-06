package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.data.PowerType;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

class StraightFlushStrategyGenerator implements GeneratorStrategy {
    @Override
    public Combination generate() {
        final List<CardType> cards = CardType.getAllCardsAsList();

        final CardType randomCard = RandomUtils.getRandomCard(cards);

        if (randomCard.getPower() == PowerType.A_POWER) {
            return generateWithAce(cards, randomCard);
        }

        final List<CardType> suitCards = cards.stream()
                .filter(cardType -> cardType.getSuitType().equals(randomCard.getSuitType()))
                .collect(Collectors.toList());

        final int start = RandomUtils.random.nextInt(7) + 1;

        final List<CardType> straightFlush = suitCards.subList(start, start + 5);

        return Combination.of(CombinationType.STRAIGHT_FLUSH, straightFlush);
    }

    private Combination generateWithAce(List<CardType> cards, CardType cardType) {
        final List<CardType> straightFlushWithAce = cards.stream()
                .filter(filter -> filter.getSuitType().equals(cardType.getSuitType()) &&
                        (filter.getPower() == PowerType.FIVE_POWER ||
                                filter.getPower() == PowerType.FOUR_POWER ||
                                filter.getPower() == PowerType.THREE_POWER ||
                                filter.getPower() == PowerType.TWO_POWER)
                )
                .collect(Collectors.toList());

        straightFlushWithAce.add(cardType);

        return Combination.of(CombinationType.STRAIGHT_FLUSH, straightFlushWithAce);
    }
}
