package ru.smn.combination.strategy.search;

import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;

import java.util.List;

import static ru.smn.combination.utils.CardUtils.findFlushBySuit;

class FlushSearchStrategy implements SearchStrategy {

    @Override
    public Combination find(List<Card> cards) {
        for (Card.SuitType suitType : Card.SuitType.values()) {
            final List<Card> flushBySuit = findFlushBySuit(cards, suitType);
            if (!flushBySuit.isEmpty()) {
                final CombinationType flush = CombinationType.FLUSH;
                return Combination.of(
                        flush,
                        flushBySuit
                );
            }
        }
        return Combination.empty();
    }
}
