package ru.smn.combination;

import org.springframework.stereotype.Service;
import ru.smn.combination.data.Card;
import ru.smn.combination.data.Combination;
import ru.smn.combination.data.CombinationType;
import ru.smn.combination.strategy.search.SearchAssistant;
import ru.smn.combination.utils.ComparatorUtils;

import java.util.Arrays;
import java.util.List;

import static ru.smn.combination.data.CardSizeData.COMBINATION_SIZE;

@Service
public class ClassicCombinationService implements CombinationService {

    @Override
    public Combination findCombination(List<Card> cards) {
        if (cards.size() < COMBINATION_SIZE) {
            throw new RuntimeException("cards size cannot be less than " + COMBINATION_SIZE);
        }

        final List<CombinationType> sortedCombinationTypes = Arrays.stream(CombinationType.values())
                .sorted(ComparatorUtils.desc(CombinationType::getPower)).toList();

        for (CombinationType combinationType : sortedCombinationTypes) {
            final Combination combination = SearchAssistant.find(combinationType, cards);
            if (!combination.isEmpty()) {
                return combination;
            }
        }

        throw new RuntimeException("global error, could not found poker combination");
    }

}
