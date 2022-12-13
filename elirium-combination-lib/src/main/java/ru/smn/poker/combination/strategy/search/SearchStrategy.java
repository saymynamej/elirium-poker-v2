package ru.smn.poker.combination.strategy.search;

import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;
import ru.smn.poker.combination.data.CombinationType;

import java.util.List;

interface SearchStrategy {
    /**
     * This is the search strategy interface for find poker combinations.
     * Any implements of this interface must return {@link Combination#empty} if combination is not found,
     * otherwise must return one of combination in the {@link CombinationType}
     *
     * @param cards must have five or more cards for find
     * @return Poker combination if it was found otherwise empty
     */
    Combination find(List<Card> cards);
}
