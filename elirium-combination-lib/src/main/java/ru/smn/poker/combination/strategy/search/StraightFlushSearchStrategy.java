package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.List;

import static ru.smn.poker.combination.utils.CardUtils.checkStraitWithAce;
import static ru.smn.poker.combination.utils.CardUtils.isStrait;

class StraightFlushSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final Combination flush = SearchAssistant.find(CombinationType.FLUSH, cards);

        if (!flush.isEmpty() && isStrait(flush.getCards())) {
            return Combination.of(CombinationType.STRAIGHT_FLUSH, flush.getCards());
        }

        final List<Card> straitWithAce = checkStraitWithAce(cards);
        if (!straitWithAce.isEmpty()) {
            final Combination straitFlushWithAce = SearchAssistant.find(CombinationType.FLUSH, straitWithAce);
            if (!straitFlushWithAce.isEmpty()) {
                return Combination.of(CombinationType.STRAIGHT_FLUSH, straitFlushWithAce.getCards());
            }
        }
        return Combination.empty();
    }
}
