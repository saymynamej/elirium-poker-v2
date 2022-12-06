package ru.smn.combination.strategy.search;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

import java.util.List;

import static ru.smn.combination.utils.CardUtils.checkStraitWithAce;
import static ru.smn.combination.utils.CardUtils.isStrait;

class StraightFlushSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<CardType> cards) {
        final Combination flush = SearchAssistant.find(CombinationType.FLUSH, cards);

        if (!flush.isEmpty() && isStrait(flush.getCards())) {
            return Combination.of(CombinationType.STRAIGHT_FLUSH, flush.getCards());
        }

        final List<CardType> straitWithAce = checkStraitWithAce(cards);
        if (!straitWithAce.isEmpty()) {
            final Combination straitFlushWithAce = SearchAssistant.find(CombinationType.FLUSH, straitWithAce);
            if (!straitFlushWithAce.isEmpty()) {

                return Combination.of(CombinationType.STRAIGHT_FLUSH, straitFlushWithAce.getCards());
            }
        }
        return Combination.empty();
    }
}
