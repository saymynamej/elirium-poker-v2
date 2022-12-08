package ru.smn.combination;


import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;

import java.util.List;

public interface CombinationService {
    Combination findCombination(List<Card> cards);
}
