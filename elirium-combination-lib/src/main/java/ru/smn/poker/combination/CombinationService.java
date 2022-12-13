package ru.smn.poker.combination;


import ru.smn.poker.combination.data.Card;
import ru.smn.poker.combination.data.Combination;

import java.util.List;

public interface CombinationService {
    Combination findCombination(List<Card> cards);
}
