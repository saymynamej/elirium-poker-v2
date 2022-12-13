package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.CardSizeData;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;
import ru.smn.poker.combination.utils.CardUtils;

import java.util.List;

import static ru.smn.poker.combination.utils.CardUtils.*;

class StraightSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final List<Card> sortedCards = sortByDesc(distinctByPowerType(cards));
        final CombinationType straight = CombinationType.STRAIGHT;

        if (sortedCards.size() < CardSizeData.COMBINATION_SIZE) {
            return Combination.empty();
        }

        for (int i = 0; i < sortedCards.size() % 4; i++) {
            final List<Card> cardTypes = sortedCards.subList(i, CardSizeData.COMBINATION_SIZE + i);
            if (CardUtils.isStrait(cardTypes)) {
                return Combination.of(straight, cardTypes);
            }
        }

        final List<Card> straightWithAce = checkStraitWithAce(cards);

        if (!straightWithAce.isEmpty()) {
            return Combination.of(straight, straightWithAce);
        }

        return Combination.empty();
    }
}
