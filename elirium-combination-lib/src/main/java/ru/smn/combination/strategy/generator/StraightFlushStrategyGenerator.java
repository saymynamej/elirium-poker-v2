package ru.smn.combination.strategy.generator;

import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.data.PowerType;
import ru.smn.combination.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

class StraightFlushStrategyGenerator implements GeneratorStrategy {
    @Override
    public Combination generate() {
        final List<Card> cards = Card.getAllCardsAsList();

        final Card randomCard = RandomUtils.getRandomCard(cards);

        if (randomCard.getPower() == PowerType.A_POWER) {
            return generateWithAce(cards, randomCard);
        }

        final List<Card> suitCards = cards.stream()
                .filter(cardType -> cardType.getSuitType().equals(randomCard.getSuitType()))
                .collect(Collectors.toList());

        final int start = RandomUtils.random.nextInt(7) + 1;

        final List<Card> straightFlush = suitCards.subList(start, start + 5);

        return Combination.of(CombinationType.STRAIGHT_FLUSH, straightFlush);
    }

    private Combination generateWithAce(List<Card> cards, Card card) {
        final List<Card> straightFlushWithAce = cards.stream()
                .filter(filter -> filter.getSuitType().equals(card.getSuitType()) &&
                        (filter.getPower() == PowerType.FIVE_POWER ||
                                filter.getPower() == PowerType.FOUR_POWER ||
                                filter.getPower() == PowerType.THREE_POWER ||
                                filter.getPower() == PowerType.TWO_POWER)
                )
                .collect(Collectors.toList());

        straightFlushWithAce.add(card);

        return Combination.of(CombinationType.STRAIGHT_FLUSH, straightFlushWithAce);
    }
}
