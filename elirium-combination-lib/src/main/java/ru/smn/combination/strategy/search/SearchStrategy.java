package ru.smn.combination.strategy.search;

import ru.smn.combination.data.CardType;
import ru.smn.combination.data.Combination;

import java.util.List;

interface SearchStrategy {
    /**
     * This is the search strategy interface for find poker combinations.
     * Any implements of this interface must return {@link Combination#empty} if combination is not found,
     * otherwise must return one of combination in the {@link ru.smn.combination.data.CombinationType}
     *
     * @param cards must have five or more cards for find
     * @return Poker combination if it was found otherwise empty
     */
    Combination find(List<CardType> cards);
}
