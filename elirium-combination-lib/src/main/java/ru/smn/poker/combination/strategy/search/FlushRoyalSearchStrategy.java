package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.List;

import static ru.smn.poker.combination.utils.CardUtils.containsAllCardsForFlushRoyal;

class FlushRoyalSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        final Combination flush = SearchAssistant.find(CombinationType.FLUSH, cards);
        if (!flush.isEmpty() && containsAllCardsForFlushRoyal(flush.getCards())) {
            final CombinationType flushRoyal = CombinationType.FLUSH_ROYAL;
            return Combination.of(flushRoyal, flush.getCards());
        }
        return Combination.empty();
    }
}
